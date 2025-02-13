# Instagram Video Scraper

**Instagram Video Scraper**, girdiğiniz kullanıcı adındaki Instagram hesabının ilk 12 postundaki videoları otomatik olarak indirir (eğer profil açıksa).

## Öne Çıkan Özellikler

- Instagram API'si ile veri çekme
- JSON formatında veri işleme
- Video URL'lerini ve izlenme sayılarını alma
- Video olan postları tespit edip indirir
- Basit bir Java Swing arayüzü ile kullanıcı dostu deneyim

## Gereksinimler

- **Java 23**
- **OkHttp** (API'ye istek göndermek için)
- **Jackson** (JSON verilerini işlemek için)
- **Dotenv** (API anahtarını güvenli bir şekilde saklamak için)

## Maven ile Proje Kurulumu

Projeyi çalıştırmak için Maven kullanabilirsiniz. Gerekli bağımlılıkları **`pom.xml`** dosyasında bulabilirsiniz.

### Bağımlılıklar:

<!-- HTTP İstekleri için OkHttp -->
    <dependency>
      <groupId>com.squareup.okhttp3</groupId>
      <artifactId>okhttp</artifactId>
      <version>4.9.3</version>
    </dependency>

    <!-- JSON Parse için Jackson -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.14.1</version>
    </dependency>
    <dependency>
      <groupId>org.json</groupId>
      <artifactId>json</artifactId>
      <version>20210307</version>
    </dependency>
    <dependency>
      <groupId>io.github.cdimascio</groupId>
      <artifactId>dotenv-java</artifactId>
      <version>3.0.0</version>
    </dependency>

## Nasıl Çalıştırılır?

1. **API anahtarınızı alın**  
   Instagram API'si için bir anahtar alın ve `.env` dosyasına aşağıdaki formatta ekleyin:
   
String API_KEY=YOUR_API_KEY


2. **Projeyi çalıştırın**  
Projeyi IntelliJ IDEA veya başka bir Java IDE'sinde açın. Projeyi çalıştırmadan önce `.env` dosyasını doğru şekilde yapılandırdığınızdan emin olun.

3. **Kullanıcı adı girin**  
Java Swing arayüzü açıldığında, text field'a istediğiniz Instagram kullanıcı adını yazın ve "İndir" butonuna tıklayın.

4. **Videoları indirin**  
Uygulama, girilen kullanıcı adının ilk 12 postunu kontrol eder ve videoları otomatik olarak indirir.


## Teknolojiler

- **OkHttp**: API ile iletişim kurmak için.
- **Jackson JSON**: API'den gelen JSON verilerini işlemek için.
- **Swing**: Basit bir kullanıcı arayüzü oluşturmak için.




