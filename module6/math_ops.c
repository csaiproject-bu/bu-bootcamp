#include <stdio.h>

void print_math(int a, int b) {
    printf("Sum:     %d\n", a + b);
    printf("Product: %d\n", a * b);
}

int main(void) {
    int a, b;

    printf("Enter first number:  ");
    scanf("%d", &a);

    printf("Enter second number: ");
    scanf("%d", &b);

    print_math(a, b);

    return 0;
}