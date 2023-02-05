`timescale 1 ns/10 ps
module full_adder_tb;

    reg A, B, Cin;
    wire S, Cout;

    full_adder test_full_adder(.A(A), .B(B), .Cin(Cin), .S(S), .Cout(Cout));

    initial begin
        $dumpfile("full_adder.vcd");
        $dumpvars;

        for (integer A_int = 0; A_int < 2; A_int++) begin
            A = A_int;
            for (integer B_int = 0; B_int < 2; B_int++) begin
                B = B_int;
                for (integer Cin_int = 0; Cin_int < 2; Cin_int++) begin
                    Cin = Cin_int;
                    #50;
                end
            end
        end


    end


endmodule