package com.ahmet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCustomerRequestDto {
    @NotBlank(message = "Kullanıcı adını boş bırakmayınız")
    @Size(min=3,max=20,message = "Kullanıcı adı en az 3 en fazla 20 karakter olabilir.")
    private  String  name;
    @NotBlank(message = "Kullanıcı adresi boş bırakmayınız")
    @Size(min=3,max=20,message = "Kullanıcı adı en az 3 en fazla 20 karakter olabilir.")
    private  String address;
    @Email
    @NotBlank(message = "Maili boş geçemezsiniz")
    private String email;
    @NotBlank(message = "Şifreyi boş gemezsiniz")
    @Size(min = 8, max = 64)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$"
            ,message = "Şifreniz en az büyük harf , küçük harf , bir sayı ve özel karakterden oluşmalıdır")
    private String password;
    private String Repassword;
    @NotBlank(message = "Cart bilgileri boş bırakmayınız")
    @Size(min=3,max=20,message = "Kullanıcı adı en az 3 en fazla 20 karakter olabilir.")
    private String cardDetails;
    private Double balance;
}
