# 0x400 (si oznacimo, da .data zacne na 0x400)
.data
A: .word -1

# zdaj pa ukazi
.text
# addi je add immediate
# sestejemo kostanto 0 (x0) in -2.
# Torej to je ekvivalentno zapisa vrednosti v določen register (x4)
# add immediate in the registed x4 the value from x0 and the constant -2
addi x4, x0, -2