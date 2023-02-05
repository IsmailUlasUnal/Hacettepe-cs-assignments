`timescale 1ns/10ps
module four_bit_2x1_mux_tb;

	reg[3:0] In_1;
	reg[3:0] In_0;
	reg Select;
	wire[3:0] Out;

	four_bit_2x1_mux test_2x1_mux(.In_1(In_1), .In_0(In_0), .Select(Select), .Out(Out));

	initial begin
        $dumpfile("four_bit_2x1_mux.vcd");
        $dumpvars;

		for (integer select_int = 0; select_int < 2; select_int++) begin 
			Select = select_int;
			for (integer In_1_int = 0; In_1_int < 16; In_1_int++) begin
				In_1 = In_1_int;
				for (integer In_0_int = 0; In_0_int < 16; In_0_int++) begin
					In_0 = In_0_int;
					#50;
				end
			end
		end
		
		$finish;
		

	end
endmodule

