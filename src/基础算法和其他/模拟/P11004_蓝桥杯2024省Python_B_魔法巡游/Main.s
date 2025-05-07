section .data
    input_fmt db "%d", 0
    output_fmt db "%d", 10, 0
    ten dd 10

section .bss
    si resd 100001
    ti resd 100001
    n resd 1

section .text
    global main
    extern scanf
    extern printf

check_number:
    test edi, edi
    jz .true
    mov eax, edi
.loop:
    test eax, eax
    jz .false
    mov edx, 0
    div dword [ten]
    cmp edx, 0
    je .true
    cmp edx, 2
    je .true
    cmp edx, 4
    je .true
    jmp .loop
.true:
    mov eax, 1
    ret
.false:
    xor eax, eax
    ret

main:
    push rbp
    mov rbp, rsp

    ; 读取n
    lea rdi, [input_fmt]
    lea rsi, [n]
    xor eax, eax
    call scanf

    ; 读取si数组
    mov ebx, 1
.read_si:
    cmp ebx, [n]
    jg .read_ti
    lea rdi, [input_fmt]
    lea rsi, [si + ebx*4]
    xor eax, eax
    call scanf
    inc ebx
    jmp .read_si

.read_ti:
    mov ebx, 1
.read_ti_loop:
    cmp ebx, [n]
    jg .process
    lea rdi, [input_fmt]
    lea rsi, [ti + ebx*4]
    xor eax, eax
    call scanf
    inc ebx
    jmp .read_ti_loop

.process:
    mov ebx, 1       ; i=1
    mov r12d, 1      ; inp=1
    xor r13d, r13d  ; count=0

.loop:
    cmp ebx, [n]
    jg .end

    test r12d, 1
    jz .use_ti

.use_si:
    mov edi, [si + ebx*4]
    call check_number
    test eax, eax
    jz .next
    inc r13d
    inc r12d
    jmp .next

.use_ti:
    mov edi, [ti + ebx*4]
    call check_number
    test eax, eax
    jz .next
    inc r13d
    inc r12d

.next:
    inc ebx
    jmp .loop

.end:
    ; 输出结果
    lea rdi, [output_fmt]
    mov esi, r13d
    xor eax, eax
    call printf

    mov rsp, rbp
    pop rbp
    xor eax, eax
    ret