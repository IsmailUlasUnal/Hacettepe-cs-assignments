`timescale 1ns/1ps

module counter_tb;
    reg reset, clk, mode; 
    wire [2:0] count;
    integer i;
	
	//Comment the next line out when testing your JK flip flop implementation.
    counter_d uut(reset, clk, mode, count);
    // Uncomment the next line to test your JK flip flop implementation.
    //counter_jk c1(reset, clk, mode, count);

    initial begin
        $dumpfile("result.vcd");
        $dumpvars;

        reset = 1; // in the beginning reset will be 1
        mode = 0; // in the beginning mode will be 0 because I will try to solve 
        #75; // starts from here
        reset = 0; // make reset is 0 and run code
        #500; 
        mode = 1; // for checking the gray code change mode into 1
        #500;
        reset = 1; // finish by making reset 1
        #75;
        $finish;
    end


    initial begin
        // clock implementation
        forever begin
            #20 clk = 1;
            #20 clk = 0;
        end

    end

endmodule