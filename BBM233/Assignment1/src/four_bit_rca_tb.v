`timescale 1 ns/10 ps

module four_bit_rca_tb;

  reg[3:0] A, B;
  reg Cin;

  wire[3:0] S;
  wire Cout;

  integer A_int, B_int, Cin_int;

  four_bit_rca test_four_bit_rca(.A(A), .B(B), .Cin(Cin), .S(S), .Cout(Cout));

  initial begin
    $dumpfile("four_bit_rca.vcd");
    $dumpvars;

    for (Cin_int = 0; Cin_int < 2; Cin_int++) begin
      Cin = Cin_int;
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