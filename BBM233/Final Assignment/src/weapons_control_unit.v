`timescale 1us/1ns

module weapons_control_unit(
    input target_locked,
    input clk,
    input rst,
    input fire_command,
    output reg launch_missile,
    output reg [3:0] remaining_missiles,
    output reg [1:0] WCU_state
);

    // states 
    localparam IDLE = 2'b00,
        TARGET_LOCKED = 2'b01,
        FIRE = 2'b10,
        OUT_OF_AMMO = 2'b11;

    reg[1:0] state; // current state we are using this for checking our state

    // when we take launch_missile command if have missiles it will decreate it's count by 1
    always @(posedge launch_missile) begin 
        if (remaining_missiles > 0) begin
            remaining_missiles <= remaining_missiles - 1;
        end
    end

    // when target locked becomes false make launch missile 0 and change state to idle state
    always @(*) begin
        if (!target_locked) begin
            launch_missile <= 0;
            WCU_state <= IDLE;
            state <= IDLE;
        end
    end
 
    always @(posedge clk) begin
        if (rst) begin // reset, in idle state with 4 missiles
            WCU_state <= IDLE;
            state <= IDLE;
            remaining_missiles <= 4;
            launch_missile <= 0;
        end else begin
             
            if (state == IDLE) begin // current state is idle state

                if (target_locked) begin // if we locked the target make target locked as 1
                    WCU_state <= TARGET_LOCKED;
                    state <= TARGET_LOCKED;
                end

            end else if (state == TARGET_LOCKED) begin // current state is target locked state

                if (!target_locked) begin // if we didn't locked the target make idle state
                    WCU_state <= IDLE;
                    state <= IDLE;
                end else if (fire_command) begin // if fire command comes make state as 1 and launch missile as 1
                    if (remaining_missiles > 0) begin
                        WCU_state <= FIRE;
                        state <= FIRE;
                        launch_missile <= 1;
                    end
                end

            end else if (state == FIRE) begin // current state is fire state
                
                if (remaining_missiles == 0) begin // if we don't have any missiles make state as out of ammo
                    WCU_state <= OUT_OF_AMMO;
                    state <= OUT_OF_AMMO;
                    launch_missile <= 0;
                end else if (target_locked) begin // if we locked the target make state as target locked
                    WCU_state <= TARGET_LOCKED;
                    state <= TARGET_LOCKED;
                    launch_missile <= 0;
                end else if (!target_locked) begin // if we didn't locked the target make it idle state
                    WCU_state <= IDLE;
                    state <= IDLE;
                    launch_missile <= 0;
                end

            end
        end
    end  
endmodule