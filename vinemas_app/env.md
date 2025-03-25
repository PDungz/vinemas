# API Configuration Guide (English & Vietnamese)

## 1. API Key Gemini
**English:** API Key for accessing Gemini services:
```ini
API_KEY_GEMINI = "<YOUR_GEMINI_API_KEY>"
```
> 🔹 Replace `<YOUR_GEMINI_API_KEY>` with the actual API Key from [Google AI Studio](https://aistudio.google.com/apikey).

**Vietnamese:** API Key dùng để truy cập dịch vụ Gemini:
```ini
API_KEY_GEMINI = "<YOUR_GEMINI_API_KEY>"
```
> 🔹 Thay `<YOUR_GEMINI_API_KEY>` bằng API Key thực tế từ [Google AI Studio](https://aistudio.google.com/apikey).

## 2. Stripe Payment Configuration / Cấu Hình Thanh Toán Stripe
**English:**
```ini
URL_PAY = "https://api.stripe.com/v1/payment_intents"
PUBLISHABLE_KEY = "<YOUR_STRIPE_PUBLISHABLE_KEY>"
SECRET_KEY = "<YOUR_STRIPE_SECRET_KEY>"
```
> 🔹 Replace `<YOUR_STRIPE_PUBLISHABLE_KEY>` and `<YOUR_STRIPE_SECRET_KEY>` with actual credentials from Stripe.

**Vietnamese:**
```ini
URL_PAY = "https://api.stripe.com/v1/payment_intents"
PUBLISHABLE_KEY = "<YOUR_STRIPE_PUBLISHABLE_KEY>"
SECRET_KEY = "<YOUR_STRIPE_SECRET_KEY>"
```
> 🔹 Thay `<YOUR_STRIPE_PUBLISHABLE_KEY>` và `<YOUR_STRIPE_SECRET_KEY>` bằng thông tin thực tế từ tài khoản Stripe.

## 3. TMDb API Configuration / Cấu Hình API TMDb
**English:**
```ini
API_HOST = "https://api.themoviedb.org/"
API_VERSION = "3"
API_KEY = "<YOUR_TMDB_API_KEY>"
```
> 🔹 Register for an API Key at [TMDb](https://www.themoviedb.org/settings/api) and replace `<YOUR_TMDB_API_KEY>`.

**Vietnamese:**
```ini
API_HOST = "https://api.themoviedb.org/"
API_VERSION = "3"
API_KEY = "<YOUR_TMDB_API_KEY>"
```
> 🔹 Đăng ký API Key tại [TMDb](https://www.themoviedb.org/settings/api) và thay vào `<YOUR_TMDB_API_KEY>`.

## 4. API Configuration Endpoints / Đường Dẫn API Cấu Hình
**English:**
```ini
API_CONFIGURATION_DETAILS = "/configuration"
API_CONFIGURATION_COUNTRIES = "/configuration/countries?language=en-US"
API_CONFIGURATION_JOBS = "/configuration/jobs"
API_CONFIGURATION_LANGUAGES = "/configuration/languages"
API_CONFIGURATION_PRIMARY_TRANSLATIONS = "/configuration/primary_translations"
API_CONFIGURATION_TIMEZONES = "/configuration/timezones"
```
**Vietnamese:**
```ini
API_CONFIGURATION_DETAILS = "/configuration"
API_CONFIGURATION_COUNTRIES = "/configuration/countries?language=vi-VN"
API_CONFIGURATION_JOBS = "/configuration/jobs"
API_CONFIGURATION_LANGUAGES = "/configuration/languages"
API_CONFIGURATION_PRIMARY_TRANSLATIONS = "/configuration/primary_translations"
API_CONFIGURATION_TIMEZONES = "/configuration/timezones"
```

## 5. API Genres / API Thể Loại
**English:**
```ini
API_GENRES_MOVIE_LIST = "/genre/movie/list"
API_GENRES_TV_LIST = "/genre/tv/list"
```
**Vietnamese:**
```ini
API_GENRES_MOVIE_LIST = "/genre/movie/list"
API_GENRES_TV_LIST = "/genre/tv/list"
```

## 6. API Movie Lists / Danh Sách Phim
**English:**
```ini
API_MOVIE_LIST_NOW_PLAYING = "/movie/now_playing"
API_MOVIE_LIST_POPULAR = "/movie/popular"
API_MOVIE_LIST_TOP_RATED = "/movie/top_rated"
API_MOVIE_LIST_UPCOMING = "/movie/upcoming"
```
**Vietnamese:**
```ini
API_MOVIE_LIST_NOW_PLAYING = "/movie/now_playing"
API_MOVIE_LIST_POPULAR = "/movie/popular"
API_MOVIE_LIST_TOP_RATED = "/movie/top_rated"
API_MOVIE_LIST_UPCOMING = "/movie/upcoming"
```

## 7. API Movie Details / Chi Tiết Phim
**English:**
```ini
API_MOVIE_DETAILS = "/movie/"
API_MOVIE_ALTERNATIVE_TITLES = "/movie/{movie_id}/alternative_titles"
API_MOVIE_IMAGES = "/movie/{movie_id}/images"
API_MOVIE_RECOMMENDATIONS = "/movie/{movie_id}/recommendations"
API_MOVIE_RELEASE_DATES = "/movie/{movie_id}/release_dates"
API_MOVIE_SIMILAR = "/movie/{movie_id}/similar"
API_MOVIE_TRANSLATIONS = "/movie/{movie_id}/translations"
API_MOVIE_VIDEOS = "/movie/{movie_id}/videos"
API_MOVIE_WATCH_PROVIDERS = "/movie/{movie_id}/watch/providers"
```
**Vietnamese:**
```ini
API_MOVIE_DETAILS = "/movie/"
API_MOVIE_ALTERNATIVE_TITLES = "/movie/{movie_id}/alternative_titles"
API_MOVIE_IMAGES = "/movie/{movie_id}/images"
API_MOVIE_RECOMMENDATIONS = "/movie/{movie_id}/recommendations"
API_MOVIE_RELEASE_DATES = "/movie/{movie_id}/release_dates"
API_MOVIE_SIMILAR = "/movie/{movie_id}/similar"
API_MOVIE_TRANSLATIONS = "/movie/{movie_id}/translations"
API_MOVIE_VIDEOS = "/movie/{movie_id}/videos"
API_MOVIE_WATCH_PROVIDERS = "/movie/{movie_id}/watch/providers"
```

## 8. API Cast / Diễn Viên
**English:**
```ini
API_MOVIE_CAST = "/movie/{movie_id}/credits"
```
**Vietnamese:**
```ini
API_MOVIE_CAST = "/movie/{movie_id}/credits"
```

## 9. API Search / API Tìm Kiếm
**English:**
```ini
API_SEARCH_COLLECTION = "/search/collection"
API_SEARCH_COMPANY = "/search/company"
API_SEARCH_KEYWORD = "/search/keyword"
API_SEARCH_MOVIE = "/search/movie"
API_SEARCH_MULTI = "/search/multi"
```
**Vietnamese:**
```ini
API_SEARCH_COLLECTION = "/search/collection"
API_SEARCH_COMPANY = "/search/company"
API_SEARCH_KEYWORD = "/search/keyword"
API_SEARCH_MOVIE = "/search/movie"
API_SEARCH_MULTI = "/search/multi"
```

---
### 📌 Notes / Ghi chú:
1. **English:** Replace API Keys with actual values to use the API.
2. **Vietnamese:** Thay thế các API Key bằng thông tin thực tế để có thể sử dụng API.
3. **English:** For endpoints with `{movie_id}`, replace with the actual movie ID.
4. **Vietnamese:** Các endpoint có `{movie_id}` cần được thay thế bằng ID của phim mong muốn.

🔗 **Enjoy using the API! / Chúc bạn sử dụng API thành công!** 🎬

