package com.eservetti.ejercicio_hashing;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EjercicioHashingApplication {
    private static void testHashBCrypt(String password, int strength) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength);
        long start = System.currentTimeMillis();
        String hashedPassword = encoder.encode(password);
        long end = System.currentTimeMillis();

        System.out.println("Contraseña hasheada por Bcrypt: " + hashedPassword);
        System.out.println("Hash Strength Bcrypt: " + strength);
        System.out.println("Tiempo en ms: " + (end - start));
    }

	public static void main(String[] args) {
		SpringApplication.run(EjercicioHashingApplication.class, args);
        int hashLen = 12;
        String rawPassword = "utn_2026";
        System.out.println("Contraseña sin modificaciones: " + rawPassword);
        System.out.println("Longitud del hash: " + hashLen);
        // Hashing por MD5
        String hash1 = DigestUtils.md5Hex(rawPassword);
        String hash2 = DigestUtils.md5Hex(rawPassword);

        System.out.println("Hash por MD5 1: " + hash1);
        System.out.println("Hash por MD5 2: " + hash2);
        System.out.println("Longitud del hash MD5: " + hashLen);
        // Hashing por BCrypt
        testHashBCrypt(rawPassword, 4);
        testHashBCrypt(rawPassword, 10);
        testHashBCrypt(rawPassword, 15);
        // Hashing por Argon
        Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, hashLen,1, 65536,3);
        System.out.println("Hash Argon: " + argonEncoder.encode(rawPassword));
	}

}
