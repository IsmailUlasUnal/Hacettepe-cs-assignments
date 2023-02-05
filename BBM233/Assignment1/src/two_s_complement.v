module two_s_complement(In,Out);
    input [3:0] In;
    output [3:0] Out;

    wire [3:0] not_In;

    assign not_In = ~In;
    assign Out = not_In + 1;    
    
endmodule  
