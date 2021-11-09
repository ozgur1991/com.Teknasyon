@teknasyon
Feature: US_001 Teknasyon web Sitesi Ziyaret ve Basvuru Yapabilme Testi

  Scenario: Kullanici google da arama yapar

    Given kulanici "https://www.google.com.tr" adresini ziyaret eder
    And  "https://www.google.com.tr" adresini dogrular
    Then istenen "Teknasyon" kelimeyi arar

  Scenario: Kullanici teknasyon adresinde
    And  istenen "https://teknasyon.com" adresinin listelendigini dogrular ve adrese tiklar
    And  sitenin basliginin "Teknasyon" icerdigini kontrol eder



Scenario: Kullanici Kariyer sayfasinda oldugunu dogruluyor
    Given istenen "2" numarali "Kariyer" linkine scroll yapar
    Then istenen "2" numarali "Kariyer" butonun gorunurlugunu test eder
    And istenen "2" numarali "Kariyer" butonuna tiklar
    Then "Kariyer" sayfasinda oldugunu dogrular

  Scenario: Kullanici Test Otomasyon Muhendisligi pozisyonunu dogruluyor ve basvuruyor
   Then "Test Otomasyon Mühendisi" pozisyonunun bulundugunu dogrular
    And istenen "Test Otomasyon Mühendisi" linkine tiklar
    And  sitenin basliginin "Test Otomasyon Mühendisi" icerdigini kontrol eder
    Then istenen "1" numarali "Başvur" butonun gorunurlugunu test eder
    And istenen "Başvur" linkine tiklar

    Scenario: Kullanici basvurusunu tamamliyor
    Then sayfada reCAPTCHA oldugunu dogrular
    Then hata mesajlarinin geldigini dogrular
    Then istenen "1" numarali "CV Yükle" butonun gorunurlugunu test eder
    And "Ozgur Asula" ismiyle "cookozgur@gmail.com" adresyile basvuru formunu doldurur
    And "Ozgur_Asula_CV.pdf" dosyasini "C:\\Users\\Lenovo\\com.Teknasyon\\" pathinden yukler
    And onay kutularini doldurur
   Then hata mesajlarinin gittigini dogrular
    And Basvur butonuna tiklar

