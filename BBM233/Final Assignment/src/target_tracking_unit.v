`timescale 1us/1ns

module target_tracking_unit(
    input rst,
    input track_target_command,
    input clk,
    input echo,
    output reg trigger_radar_transmitter,
    output reg [13:0] distance_to_target,
    output reg target_locked,
    output reg [1:0] TTU_state
);
    reg seen_echo; // This is for when we seen echo we will make this as 1 because echo can come middle of clock but machine has to change its state in clock

    // parameters for every state IDLE, TRANSMIT, LISTEN_FOR_ECHO, TRACK
    localparam IDLE = 2'b00,
           TRANSMIT = 2'b01,
           LISTEN_FOR_ECHO = 2'b10,
           TRACK = 2'b11;

    parameter cons_distance = 150; // this is for the formula 10**-6 * c / 2 = 150

    reg[1:0] state; // state for changing with if else\ this is not output

    // timers for counting
    reg [14:0] transmit_start_time; 
    reg [14:0] listen_for_echo_timer;
    reg [14:0] target_update_timer;

    always @(posedge echo) begin // when we seen echo it will calculate distance and make seen_echo 1 for changing state
        seen_echo = 1'b1;
        distance_to_target = cons_distance * ($time - listen_for_echo_timer);   
        target_locked <= 1;                
    end


    always @(posedge track_target_command) begin // when target track command comes, trigger radar transmistter has to be 1 and become 0 after 50 interval of time
        trigger_radar_transmitter <= 1; 
        #50
        trigger_radar_transmitter <= 0;            
        listen_for_echo_timer <= $time;
    end

    always @(posedge clk) begin
        // everything will be printed in if else statements because it is a merely design
        if (rst) begin // in reset state it become idle and it will print 
            state <= IDLE;

            //outputs
            trigger_radar_transmitter <= 0;
            distance_to_target <= 0;
            target_locked <= 0;
            TTU_state <= IDLE;

        end else begin // if not reset 
            if (state == IDLE) begin // current state is idle 

                if (track_target_command) begin // if track_target_command becomes 1 we need to change state and start timer.
                    state <= TRANSMIT;
                    TTU_state <= TRANSMIT;
                    transmit_start_time <= $time;               
                end
                
            end else if (state == TRANSMIT) begin // current state is transmis
                if ($time - transmit_start_time >= 50) begin // if time interval greater than 50 we need to change our state to listen for echo 
                    state <= LISTEN_FOR_ECHO;
                    TTU_state <= LISTEN_FOR_ECHO;
                end
            end else if (state == LISTEN_FOR_ECHO) begin // current state is listen for echo 
                if ($time - listen_for_echo_timer >= 100) begin //  if time interval greater than 100 we need to go back idle state
                    state <= IDLE;
                    TTU_state <= IDLE;
                    distance_to_target <= 0;
                    target_locked <= 0;
                end else if (seen_echo) begin // if we saw echo line it change it's state 
                    seen_echo <= 1'b0;
                    state <= TRACK;
                    TTU_state <= TRACK;
                    target_update_timer <= $time;
                end 
            end else if (state == TRACK) begin // current state is track
                if ($time - target_update_timer >= 300) begin // if time interval is greater than 300 it has to change idle state
                    distance_to_target <= 0;
                    target_locked <= 0;
                    state <= IDLE;
                    TTU_state <= IDLE;
                end else if (track_target_command) begin // in 300 if we take track target command input it has to change it's state to transmit state
                    state <= TRANSMIT;
                    TTU_state <= TRANSMIT;
                    transmit_start_time <= $time;
                    target_locked <= 0;
                end
            end
        end
    end

endmodule