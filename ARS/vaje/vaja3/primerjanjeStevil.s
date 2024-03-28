.data
A: .word -16

.text
addi x1, x0, -1
# ali je vrednost v x1 manjša kot vrednost v x0? se zapiše v x2
slt x2, x1, x0

# slt unsigned
sltu x3, x1, x0