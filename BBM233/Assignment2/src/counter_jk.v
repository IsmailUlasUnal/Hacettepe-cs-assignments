module counter_jk(input reset, input clk, input mode, output [2:0] count);

    // ja, jb, and jc are the J inputs for the JK flip-flops used in the counter.
    // ka, kb, and kc are the K inputs for the JK flip-flops used in the counter.

    wire ja, jb, jc, ka, kb, kc;

    // for the input of count[2]'s jk flip flop
    assign ja = (~mode & count[1] & count[0]) | (mode & count[1] & ~count[0]); 
    assign ka = (~mode & count[1] & count[0]) | (mode & ~count[1] & ~count[0]); 

    // for the input of count[1]'s jk flip flop
    assign jb = (~mode & count[0]) | (~count[2] & count[0]);
    assign kb = (~mode & count[0]) | (count[2] & count[0]);

    // for the input of count[0]'s jk flip flop 
    assign jc = (~mode) | (~count[2] & ~count[1]) | (count[2] & count[1]);
    assign kc = (~mode) | (~count[2] & count[1]) | (count[2] & ~count[1]);


    // work with 3 JK Flip Flop because I have 8 states log2^8 = 3.
    jk_sync_res jkA(ja, ka, clk, reset, count[2]);
    jk_sync_res jkB(jb, kb, clk, reset, count[1]);
    jk_sync_res jkC(jc, kc, clk, reset, count[0]);

endmodule