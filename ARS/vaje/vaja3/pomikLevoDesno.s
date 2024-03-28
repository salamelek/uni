# 0x400
.data
A: .word -16

.text
# load word
lw x1, A(x0)

# shift left logical immediate
slli x4, x1, 4
#shift right logical immediate
srli x5, x1, 4
# shift right arithmetical immediate
# se rabi za negativna števila (čene nima smisla)
srai x5, x1, 4