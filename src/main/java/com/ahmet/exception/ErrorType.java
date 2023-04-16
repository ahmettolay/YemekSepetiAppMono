package com.ahmet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_ERROR(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4000, "Parametre Hatası", HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4100, "Email  veya şifre hatalı", HttpStatus.BAD_REQUEST),
    PASSWORD_ERROR(4200, "Şifreler aynı değil", HttpStatus.BAD_REQUEST),
    EMAIL_DUPLICATE(4300, "Bu email zaten kayıtlı", HttpStatus.BAD_REQUEST),
    ADDRESS_DUPLICATE(4301, "Bu address zaten kayıtlı", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_FOUND(4400, "Böyle bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND),
    CUSTOMERS_NOT_FOUND(4401, "Kullanıcılar bulunamadı", HttpStatus.NOT_FOUND),
    ACTIVATE_CODE_ERROR(4500, "Aktivasyon kod hatası", HttpStatus.BAD_REQUEST),
    NOT_ACTIVATE_ERROR(4501, "Aktivasyon ilemini yapınız.Kullanıcı aktif değil.", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4600,"Token Hatası" ,HttpStatus.BAD_REQUEST ),
    TOKEN_NOT_CREATED(4601,"Token Oluşturulamadı Hatası" ,HttpStatus.BAD_REQUEST ),
    RESTAURANT_ORDER_FOUND(6000, "Restoranttan sipariş edilen ürün bunulamadı.", HttpStatus.NOT_FOUND),
    RESTAURANT_NOT_FOUND(6001, "Restorant bunulamadı.", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(6001, "Ürün bunulamadı.", HttpStatus.NOT_FOUND),
    BALANCE_NOT_FOUND(6002, "Yetersiz Bakiye", HttpStatus.NOT_FOUND),
    ORDER_NOT_FOUND(6002, "Sipariş bunulamadı.", HttpStatus.NOT_FOUND);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
