.balign 4
.global main
.type main, @function
main:
    li a0, 5
    jal ra, gpio_output_enable

zanka:
    addi a0, zero, 10
    jal ra, timer_fun

    jal  zero, zanka


.global timer_fun
.type timer_fun, @function
timer_fun:
    #Vstop
	

    #Jedro
    
    # vklopi led diodo
    # li a0, 5, 1
    # li a1, 1
    # jal ra, gpio_output_val
    

    #Izstop
    

.global gpio_output_enable
.type gpio_output_en, @function
gpio_output_enable:                 # a0 je številka pina ledice
    # Vstop:
    addi sp, sp, -16                # zmanjšamo skladovni kazalec
    sw ra, 12(sp)                   # shranimo povratni naslov
    sw fp, 8(sp)                    # shranimo kazalec na okvir
    addi fp, sp, 16                 # nastavimo kazalec na okvir

    # Procedura
    li t0, 0x10012000               # t0 <- bazni naslov GPIO
    lw t1, 0x08(t0)                 # preberemo trenutno vrednost
    li t2, 0x01                     # t2 <- 1
    sll t2, t2, a0                  # premaknemo enico za ciljni pin na pozicijo 5 (..0100000)
    or t1, t1, t2                   # postavimo (set) bit, ostali se ohranijo
    sw t1, 0x08(t0)                 # vpišemo konfiguracijo v GPIO output-enable register
                                        # (ledica je vedno izhod)
    # Izstop:
    lw fp, 8(sp)                    # obnovimo prejšnjo vrednost registra fp/s0
    lw ra, 12(sp)                   # obnovimo prejšnjo vrednost povratnega naslova
    addi sp, sp, 16                 # povečamo skladovni kazalec na začetno vrednost
    ret                             # skok nazaj k klicatelju

.global gpio_output_val
.type gpio_output_val, @function
gpio_output_val:                    # a0 je pin, a1 želena vrednost na pinu
    # Vstop:
    addi sp, sp, -16                # zmanjšamo skladovni kazalec
    sw ra, 12(sp)                   # shranimo povratni naslov
    sw fp, 8(sp)                    # shranimo kazalec na okvir
    addi fp, sp, 16                 # nastavimo kazalec na okvir

    # Procedura
    li t0, 0x10012000
    lw t1, 0x0C(t0)      	    # beremo trenutno vrednost izhodov
    li t3, 0x01                     # 1
    sll t3, t3, a0                  # ...00100000   1 << pin
    xori t3, t3, -1                 # ...11011111   ~(1 << pin)     (maska)
    and t1, t1, t3                  # val & ~(1 << pin)     (da se drugi pini ne izbrišejo)
    mv t2, a1
    sll t2, t2, a0                  # a1 << pin
    or t1, t1, t2                   # val = (val & ~(1 << pin)) | (a1 << pin);
    sw t1, 0x0C(t0)      # zapišemo novo vrednost izhodov

    # Izstop:
    lw fp, 8(sp)                    # obnovimo prejšnjo vrednost registra fp/s0
    lw ra, 12(sp)                   # obnovimo prejšnjo vrednost povratnega naslova
    addi sp, sp, 16                 # povečamo skladovni kazalec na začetno vrednost
    ret


.global delay
.type delay, @function
delay:
    # Vstop:
    addi sp, sp, -16                # zmanjšamo skladovni kazalec
    sw ra, 12(sp)                   # shranimo povratni naslov
    sw fp, 8(sp)                    # shranimo kazalec na okvir
    addi fp, sp, 16                 # nastavimo kazalec na okvir

    # Procedura
    mv t1, a0
    li t0, 0
    delay_loop:
    addi t1, t1, -1                 # na osnovi odštevanja začetne vrednosti (zaposleno čakanje)
    bgt t1, t0, delay_loop

    # Izstop:
    lw fp, 8(sp)                    # obnovimo prejšnjo vrednost registra fp/s0
    lw ra, 12(sp)                   # obnovimo prejšnjo vrednost povratnega naslova
    addi sp, sp, 16                 # povečamo skladovni kazalec na začetno vrednost
    ret
