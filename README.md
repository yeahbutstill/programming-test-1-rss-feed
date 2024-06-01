# Setup Docker MySQL
```shell
docker run --rm \
--name=prog_test-db \
-e MYSQL_DATABASE=prog_test \
-e MYSQL_USER=naruto \
-e MYSQL_PASSWORD=PNSJkxXvVNDAhePMuExTBuRR \
-e MYSQL_ROOT_PASSWORD=PNSJkxXvVNDAhePMuExTBuRR \
-e TZ=Asia/Jakarta \
-p 6603:3306 \
-v "$PWD/docker/prog_test-db/conf.d":/etc/mysql/conf.d \
-v "$PWD/storage/docker/prog_testdb-data":/var/lib/mysql \
mysql:8.4
```
Atau jalankan docker compose
```shell
docker compose up
```

# Setup Connections Url DBeaver
```text
jdbc:mysql://${MYSQL_HOST:localhost}:6603/prog_test?createDatabaseIfNotExist=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true&useSSL=false
```
exec [skema ini](sql/news_article.sql)

# Run
```shell
mvn clean install spring-boot:run -DskipTests
```

# programming-test-1
## Deskripsi
Repositori ini digunakan sebagai rujukan untuk test pemrograman.

# PENTING!!!!!
Pastikan kode yang diupload melalui Google Drive mempunya hak akses public

# Spesifikasi Program
Pemohon diharapkan dapat menunjukkan pemahaman dan kemampuannya membuat program dengan membuat aplikasi sederhana yang:
* dibuat dalam bahasa yang dikuasai (java, scala, python (3.8+), php, atau lainnya)
* mendapatkan DETAIL_ARTIKEL berita dengan dua cara:
  * membuka rss links: [https://jambi.antaranews.com/rss/terkini.xml](https://jambi.antaranews.com/rss/terkini.xml)
  * halaman indeks berita: [https://www.bisnis.com/index](https://www.bisnis.com/index)
* Untuk pengambilan DETAIL_ARTIKEL dari halaman indeks berita, haruslah menggunakan XPATH
  * Silakan mengakses URL berikut ini untuk keterangan lebih lanjut: [XPATH w3schools](https://www.w3schools.com/xml/xpath_intro.asp)
* Setelah mendapatkan URL dari rss ataupun indeks berita, mengunjungi URL tersebut lalu menggunakan XPATH untuk mendapatkan Konten Lengkap.
* menyimpan DETAIL_ARTIKEL_LENGKAP ke dalam database (MYSQL)
  * Silakan melihat [skema ini](sql/news_article.sql)
* DETAIL_ARTIKEL terdiri dari:
  * URL
  * Judul
  * Waktu Tayang (berupa unix timestamp)
* DETAIL_ARTIKEL_LENGKAP terdiri dari:
  * URL
  * Judul
  * Waktu Tayang (berupa unix timestamp)
  * Konten Lengkap (bukan summary)

Catatan Tambahan:
* Aplikasi dibuat dengan konsep **MVC** yang baik
* Aplikasi yang diimplementasikan menggunakan prinsip **DRY**, **KISS** dan **SOLID** adalah nilai plus-plus
* Diharapkan dapat menggunakan **framework** bagi bahasa pemrograman yang dipilih. Jika tidak menggunakan framework, harus memastikan agar **kodenya terstruktur dengan baik**
* Tidak menggunakan crawling framework, misalnya **scrappy** di python
* **Code Readability** sangat penting
* Dilengkapi dengan **dokumentasi yang memadai**, minimal di setiap kelas ataupun fungsi yang penting
* Aplikasi yang dibuat harus disertakan dengan **langkah instalasi dan pengetesan**
* Aplikasi yang dibuat harus dizip dan dikirimkan ke alamat email yang ditentukan
* Menggunakan docker adalah nilai plus tetapi bukanlah suatu kewajiban

# Contoh DETAIL_ARTIKEL:
  * URL: **https://www.jpnn.com/news/usia-pernikahan-hampir-10-tahun-iko-uwais-ingin-tambah-momongan**
  * Judul: **Usia Pernikahan Hampir 10 Tahun, Iko Uwais Ingin Tambah Momongan**
  * Waktu Tayang: **1641367740**
  * Konten Lengkap: 
> JAKARTA - Pasangan Iko Uwais dan Audy Item ternyata memiliki keinginan untuk menambah momongan. 
Saat ini keduanya sudah dianugerahi dua anak perempuan.
"Kalau ditanya mau punya anak lagi, ya lanjut," ujar Iko Uwais di kawasan Senayan, Jakarta Pusat, belum lama ini.
Aktor 38 tahun itu tak menampik dirinya mengharapkan anak laki-laki. 
Namun, dia tak masalah jika nantinya dirinya dan sang istri dianugerahi anak perempuan lagi. 
"Ya, kalau dikasih laki-laki alhamdulillah, sedikasihnya saja," tutur Iko Uwais.
Audy mengatakan tak melakukan program khusus demi menambah momongan. 
Sebab, pasangan yang menikah pada 25 Juni 2012 itu percaya bahwa anak adalah titipan Tuhan.
Ya, kalau Allah kasih kepercayaan punya anak lagi, ya insyaallah. Yang jelas kami berdua enggak spesifik harus program," imbuhnya.# programming-test-1-rss-feed
