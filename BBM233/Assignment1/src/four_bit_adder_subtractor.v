//`include "two_s_complement.v"
//`include "four_bit_2x1_mux.v"
//`include "four_bit_rca.v"

module four_bit_adder_subtractor(A, B, subtract, Result, Cout);
    input [3:0] A;
    input [3:0] B;
    input subtract;
    output [3:0] Result;
    output Cout;

    wire [3:0] minus_B;
    wire [3:0] ready_B_for_adder_subtractor;
    wire Cin = 0;

    two_s_complement minus_version(.In(B), .Out(minus_B));
    four_bit_2x1_mux decide_plus_or_minus(.In_1(minus_B), .In_0(B), .Select(subtract), .Out(ready_B_for_adder_subtractor));
    four_bit_rca make_calculations(.A(A), .B(ready_B_for_adder_subtractor), .Cin(Cin), .S(Result), .Cout(Cout));

    
    
endmodule