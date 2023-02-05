`timescale 1us/1ns

module kizilelma_tb;

    reg rst;
    reg track_target_command; 
    reg clk; 
    reg radar_echo; 
    reg fire_command;

    wire [13:0] distance_to_target; 
    wire trigger_radar_transmitter; 
    wire launch_missile;

    wire [1:0] TTU_state;
    wire [1:0] WCU_state;
    wire [3:0] remaining_missiles;

    combat_control_unit uut(
        rst, 
        track_target_command,  
        clk,  
        radar_echo,  
        fire_command, 
        distance_to_target, 
        trigger_radar_transmitter,  
        launch_missile, 
        TTU_state, 
        WCU_state, 
        remaining_missiles);

    initial begin
        $dumpfile("result.vcd");
        $dumpvars;

        rst = 1'b1;
        track_target_command = 1'b0;
        radar_echo = 1'b0;
        fire_command = 1'b0;
        #10;
        rst = 1'b0;
        #20;
        track_target_command = 1'b1;
        #10;
        track_target_command = 1'b0;
        #70;
        radar_echo = 1'b1;
        #1;
        radar_echo = 1'b0;
        #95;
        fire_command = 1'b1;
        #20;
        fire_command = 1'b0;
        #20;
        fire_command = 1'b1;
        #20;
        fire_command = 1'b0;
        #190;
        track_target_command = 1'b1;
        #10;
        track_target_command = 1'b0;
        #190;
        track_target_command = 1'b1;
        #10;
        track_target_command = 1'b0;
        #67;
        radar_echo = 1'b1;
        #1;
        radar_echo = 1'b0;
        fire_command = 1'b1;
        #20;
        fire_command = 1'b0;
        #10;
        track_target_command = 1'b1;
        #10;
        track_target_command = 1'b0;
        #55;
        radar_echo = 1'b1;
        #1;
        radar_echo = 1'b0;
        fire_command = 1'b1;
        #20;
        fire_command = 1'b0;
        #20;
        fire_command = 1'b1;
        #20;
        fire_command = 1'b0;
        #100;
        $finish;


    end


    initial begin
        clk = 0;
        forever begin
            #5;
            clk = ~clk;
        end
    end

endmodule