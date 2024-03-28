# 0x1000 0400
.data
A: .word 16,5,4

.text
# gremo na prvi data (?)
# load upper immediate
lui x1, %hi(A)
addi x1, x1, %lo(A)

# load word (16) v x4
lw x4, 0(x1)

# pomaknemo se za 4 byte da gremo na naslednjo besedo
addi x1, x1, 4
# load
lw x5, 0(x1)
addi x1, x1, 4
lw x6, 0(x1)

# seveda ce hocemo half, rabimo h in addi 2