#include <stdio.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}
void broken_swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
}
void swap_doubles(double *a, double *b) {
    double temp = *a;
    *a = *b;
    *b = temp;
}

void swap_chars(char *a, char *b) {
    char temp = *a;
    *a = *b;
    *b = temp;
}

int main(void) {
    int x = 10;
    int y = 20;

    printf("Before swap: x = %d, y = %d\n", x, y);

    swap(&x, &y);

    printf("After swap:  x = %d, y = %d\n", x, y);

    broken_swap(x, y);
    printf("After broken_swap (values unchanged): x = %d, y = %d\n", x, y);

    double a = 1.10;
    double b = 2.11;

    printf("Before swap_doubles: a = %.2f, b = %.2f\n", a, b);

    swap_doubles(&a, &b);

    printf("After swap_doubles:  a = %.2f, b = %.2f\n", a, b);

    char char1 = 'A';
    char char2= 'Z';

    printf("Before swap_chars: c1 = %c, c2 = %c\n", char1, char2);

    swap_chars(&char1, &char2);

    printf("After swap_chars:  c1 = %c, c2 = %c\n", char1, char2);

    return 0;
}