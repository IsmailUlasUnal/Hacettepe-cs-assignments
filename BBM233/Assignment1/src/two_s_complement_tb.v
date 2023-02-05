`timescale 1ns/10ps

module two_s_complement_tb;
    reg [3:0] In;
    wire [3:0] Out;

    integer In_int;

    two_s_complement test_two_s_complement (.In(In), .Out(Out));



    initial begin
        $dumpfile("two_s_complement.vcd");
        $dumpvars;

        for (In_int = 0; In_int < 16; In_int++) begin
            In = In_int;
            #50;
        end

        $finish;
    end
endmodule