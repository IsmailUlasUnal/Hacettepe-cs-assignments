module counter_d(input reset, input clk, input mode, output [2:0] count);

    wire da, db, dc;

    // for the input of count[2]'s D flip flop
    assign da = (~mode & ~count[2] & count[1] & count[0]) | (mode & count[1] & ~count[0]) | (~mode & count[2] & ~count[1]) | (~mode & count[2] & ~count[0]) | (mode & count[2] & count[0]);

    // for the input of count[1]'s D flip flop
    assign db = (count[1] & ~count[0]) | (~mode & ~count[1] & count[0]) | (mode & ~count[2] & count[0]);

    // for the input of count[0]'s D flip flop
    assign dc = (~mode & ~count[0]) | (mode & ~count[2] & ~count[1]) | (mode & count[2] & count[1]);
    

    // work with 3 D Flip Flop because I have 8 states log2^8 = 3.
    dff_sync_res dffA(da, clk, reset, count[2]);
    dff_sync_res dffB(db, clk, reset, count[1]);
    dff_sync_res dffC(dc, clk, reset, count[0]);

endmodule
