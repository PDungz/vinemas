import 'package:flutter/material.dart';
import 'package:vinemas_app/core/common/extension/color_extension.dart';
import 'package:vinemas_app/core/config/app_color.dart';
import 'package:vinemas_app/core/theme/app_text_theme.dart';
import 'package:vinemas_app/gen/fonts.gen.dart';

class ThemeApp {
  static ThemeData darkTheme() {
    return ThemeData(
      // fontFamily
      fontFamily: FontFamily.roboto,

      // IconTheme
      iconTheme: const IconThemeData(color: AppColor.primaryIconColor),

      // Brightness
      brightness: Brightness.dark,
      primaryColor: AppColor.buttonLinerOneColor,

      // ColorScheme
      colorScheme: ColorScheme.fromSwatch(
        primarySwatch: AppColor.buttonLinerOneColor.toMaterialColor(),
        brightness: Brightness.dark,
      ).copyWith(secondary: AppColor.buttonLinerOneColor),

      // Scaffold
      scaffoldBackgroundColor: AppColor.primaryColor,

      // ScrollbarTheme
      scrollbarTheme: ScrollbarThemeData(
        thumbColor: WidgetStateProperty.all(AppColor.buttonLinerOneColor),
        trackColor: WidgetStateProperty.all(AppColor.buttonLinerOneColor),
        radius: const Radius.circular(8),
        thumbVisibility: WidgetStateProperty.all(true),
      ),

      // AppBarTheme
      appBarTheme: const AppBarTheme(
        backgroundColor: AppColor.secondaryColor,
        titleTextStyle: TextStyle(
          color: AppColor.primaryTextColor,
          fontSize: 18,
          fontWeight: FontWeight.bold,
        ),
        iconTheme: IconThemeData(color: AppColor.secondaryTextColor),
      ),

      // TextTheme
      textTheme: AppTextTheme.textTheme,

      // TabBarTheme
      tabBarTheme: TabBarTheme(
        indicatorColor: AppColor.buttonLinerTwoColor,
        unselectedLabelStyle: TextStyle(
          color: AppColor.secondaryTextColor,
          fontSize: 16,
          fontWeight: FontWeight.w700,
        ),
        labelStyle: TextStyle(
          color: AppColor.buttonLinerTwoColor,
          fontSize: 16,
          fontWeight: FontWeight.w700,
          shadows: [
            Shadow(
              blurRadius: 16,
              color: AppColor.buttonLinerTwoColor.withOpacity(.5),
            ),
          ],
        ),
        indicatorSize: TabBarIndicatorSize.tab,
        dividerColor: Colors.transparent,
      ),

      // ElevatedButtonThemeData
      elevatedButtonTheme: ElevatedButtonThemeData(
        style: ButtonStyle(
          backgroundColor: WidgetStateProperty.all(
            AppColor.buttonLinerTwoColor,
          ), // Màu nền
          foregroundColor: WidgetStateProperty.all(AppColor.primaryTextColor),
          elevation: WidgetStateProperty.all(5),
          shape: WidgetStateProperty.all(
            RoundedRectangleBorder(borderRadius: BorderRadius.circular(8)),
          ),
          textStyle: WidgetStateProperty.all(
            const TextStyle(fontWeight: FontWeight.w700, fontSize: 18),
          ),

          padding: WidgetStateProperty.all(
            const EdgeInsets.symmetric(horizontal: 30, vertical: 16),
          ),
        ),
      ),
    );
  }

  static InputDecorationTheme outlineInputTheme(Color primaryColor) {
    return InputDecorationTheme(
      filled: true,
      fillColor: primaryColor.withOpacity(0.8).withOpacity(0.1),
      border: OutlineInputBorder(
        borderRadius: BorderRadius.circular(10),
        borderSide: BorderSide(color: primaryColor.withOpacity(0.8)),
      ),
      enabledBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(10),
        borderSide: BorderSide(color: primaryColor.withOpacity(0.8)),
      ),
      focusedBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(10),
        borderSide: BorderSide(color: Colors.deepOrange),
      ),
      errorBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(10),
        borderSide: BorderSide(color: Colors.red),
      ),
      focusedErrorBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(10),
        borderSide: BorderSide(color: Colors.red),
      ),
      hintStyle: TextStyle(color: AppColor.secondaryTextColor.withOpacity(0.7)),
      labelStyle: TextStyle(color: primaryColor.withOpacity(0.8)),
      suffixStyle: TextStyle(color: primaryColor.withOpacity(0.8)),
      counterStyle: TextStyle(color: primaryColor.withOpacity(0.8)),
      prefixStyle: TextStyle(color: primaryColor.withOpacity(0.8)),
      contentPadding: EdgeInsets.symmetric(horizontal: 16, vertical: 12),
    );
  }

  static InputDecorationTheme underlineInputTheme(Color primaryColor) {
    return InputDecorationTheme(
      border: UnderlineInputBorder(borderSide: BorderSide(color: primaryColor)),
      enabledBorder: UnderlineInputBorder(
        borderSide: BorderSide(color: primaryColor),
      ),
      focusedBorder: UnderlineInputBorder(
        borderSide: BorderSide(color: primaryColor),
      ),
      errorBorder: UnderlineInputBorder(
        borderSide: BorderSide(color: primaryColor),
      ),
      focusedErrorBorder: UnderlineInputBorder(
        borderSide: BorderSide(color: primaryColor),
      ),
      hintStyle: TextStyle(color: AppColor.secondaryTextColor.withOpacity(0.7)),
      labelStyle: TextStyle(color: primaryColor.withOpacity(0.8)),
      suffixStyle: TextStyle(color: primaryColor.withOpacity(0.8)),
      counterStyle: TextStyle(color: primaryColor.withOpacity(0.8)),
      prefixStyle: TextStyle(color: primaryColor.withOpacity(0.8)),
      contentPadding: EdgeInsets.symmetric(horizontal: 16, vertical: 12),
    );
  }
}
