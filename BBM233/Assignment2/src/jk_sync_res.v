module jk_sync_res(J, K, clk, sync_reset, Q);
    input J;
    input K;
    input clk;
    input sync_reset;
    output reg Q;

    // Declare a JK flip-flop
    always @(posedge clk) begin
        if (sync_reset) begin // reset statement it assigns into 0
            Q <= 1'b0;
        end else begin
            if (J && K) begin // 11 -> complement so make it complement of Q
                Q <= ~Q;
            end else if (!J && K) begin // 01 -> reset so make it 0
                Q <= 1'b0;
            end else if (J && !K) begin // 10 -> set so make it 1
                Q <= 1'b1;
            end 
            // if it didn't go anything it means that 00 so it stays same
        end
    end
endmodule
