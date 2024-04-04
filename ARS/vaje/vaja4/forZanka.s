# v registru x4 preštejemo koliko lihih števil je v a
.data
a:  .word 5,6,7,8,9    # 0x400
b:  .word 0

.text
addi x5, x0, 5    # končni pogoj
addi x3, x0, 0    # kazalec na element
addi x6, x0, 0    # i v for zanki
add x4, x0, x0    # b (stevec lihih)
    
FOR: lw x2, a(x3)
    andi x2, x2, 0x1    # pogledamo če je število liho
    add x4, x4, x2      # prištejemo v števec če je liho ali ne
    addi x3, x3, 4      # prištejemo kazalec za 4 Byte (ker so words)
    addi x6, x6, 1      # inkrementiramo i
    blt x6, x5 FOR      # dokler je i manjši od x5 ponavljamo
    
sw x4, 0x414(x0)    # ko se vse konča, store word v 0x414