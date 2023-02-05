`timescale 1ns/1ps
module four_bit_adder_subtractor_tb;

    reg[3:0] A, B;
    reg subtract;
    
    output [3:0] Result;
    output Cout;

    integer A_int, B_int, subtract_int;

    four_bit_adder_subtractor test_four_bit_adder_subtractor(.A(A), .B(B), .subtract(subtract), .Result(Result), .Cout(Cout));


    initial begin
        $dumpfile("four_bit_adder_subtractor.vcd");
        $dumpvars;

        for (subtract_int = 0; subtract_int < 2; subtract_int++) begin
            subtract = subtract_int;
            for (A_int = 0; A_int < 16; A_int++) begin
                A = A_int;
                for (B_int = 0; B_int < 16; B_int++) begin
                    B = B_int;
                    #50;
                end
            end
        end


    end
    
endmodule
