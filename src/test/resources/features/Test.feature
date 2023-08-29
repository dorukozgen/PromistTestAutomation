Feature: Promist.com.tr Test Otomasyonu

  Scenario: Anasayfadaki Üst Kısımdaki Menüde Bulunan Sayfa Yönlendirmelerinin Çalışabilirliği
    Given "http://promist.com.tr/" websitesine git
    And Sağ üst kısımdaki menüye gel
    Then Menüdeki tüm sayfa yönlendirmelerine tıklama yap ve çalıştığını kontrol et

  Scenario: Anasayfadaki Alt Kısımdaki "Bizi Takip Edin" Bölümündeki Medya Bağlantılarının Çalışabilirliği
    Given "http://promist.com.tr/" websitesine git
    And Sayfanın en altına in
    And "Bizi Takip Edin" bölümüne gel
    Then Bölümdeki tüm medya bağlantılarına orta tıklama yap ve çalıştığını kontrol et

