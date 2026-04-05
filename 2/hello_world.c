#include <stdio.h>
#include <string.h>

int main() {
    // Initializing string with a char pointer
    char *str = "Hello world";
    int len = strlen(str);

    printf("Original String: %s\n\n", str);

    // 1. Bitwise AND each character with 127
    printf("Result of AND with 127: ");
    for (int i = 0; i < len; i++) {
        // Bitwise AND (&) with 127 clears the 8th bit
        printf("%c", str[i] & 127);
    }
    printf("\n");

    // 2. Bitwise XOR each character with 127
    printf("Result of XOR with 127: ");
    for (int i = 0; i < len; i++) {
        // Bitwise XOR (^) with 127 inverts the lower 7 bits
        printf("%c", str[i] ^ 127);
    }
    printf("\n");

    return 0;
}
