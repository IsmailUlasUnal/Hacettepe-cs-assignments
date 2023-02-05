module dff_sync_res(D, clk, sync_reset, Q);
    input D;
    input clk;
    input sync_reset;
    output reg Q;

    // Declare a flip-flop
    always @(posedge clk) begin 
        if (sync_reset) begin // reset statement it assign into 0
            Q <= 1'b0;
        end else begin // if it didn't go to reset it assigns D
            Q <= D;
        end
    end
endmodule