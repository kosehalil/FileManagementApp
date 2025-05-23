# FileManageApp


**FileManageApp** uygulamasi ile `.pdf`, `.png`, `.txt`, `.docx`,`.jpeg` ve `.pptx` uygulamayi kullanan kullanicilarin bu formatlardaki dosyalari yukleyebildigi listeleyebildigi ve silebildigi bir dosya yonetim uygulamasidir Java Spring Boot, Thymeleaf ve PostgreSQL kullanılarak gelistirilmistir.


# Ozellikler
- kullanici giris / kayit islemleri (Session tabanli)
- dosya yukleme (pdf, png, txt, docx, jpg, pptx)
- dosyalari listeleyebilme
- dosyalari silebilme
- postgresql veritabani
- bootstrap-css ile basit arayuz(tasarim bilmiyorum maalesef)

# Kurulum Adimlari

# Kullanim Gereksinimleri
- Java JDK 17+
- Maven
- PostgreSQL

# Veritabani Ayarlari Adim-1 (CREATE icin)
  kullanmak istediginiz veritabanina baglanin ilk olarak ben postgresql kullandim.

`application.properties` dosyasina kullanmak istedigin veritabanina baglayin ve bir schema olusturun benim schemam "files"

spring.application.name=FileManageApp
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.jpa.properties.hibernate.default_schema=files   # olusturdugunuz schema adini girin                          
spring.datasource.username=veritabani adinizi girin
spring.datasource.password=sifrenizi girin

server.port=8080

# Zorunlu degil sql komutlarini görmek icin ekleyebilirsiniz create ile 
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


# Veritabani Ayarlari Adim-2 (UPDATE icin) eklenen verilerin silinmemesi icin
`application.properties` dosyasi icindeki `create` kismini `update` olarak guncelliyoruz girilen veriler silinmesin diye

spring.application.name=FileManageApp
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.jpa.properties.hibernate.default_schema=olusturdugunuz schema adini girin                 
spring.datasource.username=veritabani adinizi girin
spring.datasource.password=sifrenizi girin

server.port=8080

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


# Uygulamanin Calistirilmasi

git clone https://github.com/kosehalil/FileManagement.git
cd FileManageApp
./mvnw spring-boot:run

