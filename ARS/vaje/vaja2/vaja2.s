# .data samo shranjuje spremenljivke
    .data
A:  .byte 0x45
B:  .byte 16
# byte seveda ni treba da alignamo, ker bi blo align 1
# in to bi blo vsak naslov

.align 4 
# C alignamo z naslednjim address deljivom s 4
# To je skoraj obvezno naredit, ker cene bo beseda
# razdeljena na 2 registra --> zelo pocasno
C: .word 0x12345678

D: .half -4
# D ni treba da alignamo, ker je že alignan C
# če pa bi hoteli alignat, bi dali align 2

# dejanski ukazi za procesor se začnejo brez pike
.text # ukazi za procesor
# lw x2, C(x0)    # load word, daj v register x2, Cju dodaj x0 (vzemi C)
lh x4, D(x0)
lhu x5, D(x0)    # load half unsigned, daj v register x5, izberi D

# zapiši same ničle na naslov 0x400
sw x0, 0x400(x0)    # save word x0 at address 0x400