`timescale 1us/1ns

module target_tracking_unit_tb;
    reg rst;
    reg track_target_command;
    reg clk;
    reg echo;
    wire trigger_radar_transmitter;
    wire [13:0] distance_to_target;
    wire target_locked;
    wire [1:0] TTU_state;

    target_tracking_unit uut(rst, track_target_command, clk, echo, trigger_radar_transmitter, distance_to_target, target_locked, TTU_state);

    initial begin
        $dumpfile("ttu.vcd");
        $dumpvars;

        rst = 1'b1;
        track_target_command = 1'b0;
        echo = 1'b0;
        #10;
        rst = 1'b0; 
        #18;
        track_target_command = 1'b1; 
        #12;
        track_target_command = 1'b0; 
        #70;
        echo = 1'b1; 
        #1;
        echo = 1'b0;
        #350;
        track_target_command = 1'b1; 
        #10;
        track_target_command = 1'b0; 
        #190;
        track_target_command = 1'b1; 
        #10;
        track_target_command = 1'b0; 
        #75;
        echo = 1'b1; 
        #1;
        echo = 1'b0; 
        #25;
        track_target_command = 1'b1; 
        #10;
        track_target_command = 1'b0; 
        #65;
        echo = 1'b1; 
        #1;
        echo = 1'b0; 
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