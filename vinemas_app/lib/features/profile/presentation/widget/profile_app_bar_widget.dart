import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get.dart';
import 'package:packages/widget/App_bar/custom_app_bar.dart';
import 'package:packages/widget/Button/custom_icon_button.dart';
import 'package:vinemas_app/core/common/enum/process_status.dart';
import 'package:vinemas_app/core/config/app_router.dart';
import 'package:vinemas_app/features/login/presentation/bloc/bloc/user_bloc.dart';
import 'package:vinemas_app/gen/assets.gen.dart';
import 'package:vinemas_app/l10n/generated/app_localizations.dart';

class ProfileAppBarWidget extends StatelessWidget {
  const ProfileAppBarWidget({super.key});

  @override
  Widget build(BuildContext context) => BlocListener<UserBloc, UserState>(
    listener: (context, state) {
      if (state is logoutState &&
          state.processStatus == ProcessStatus.success) {
        Get.offAllNamed(ConfigRoute.loginPage);
      } else {
        Get.toNamed(ConfigRoute.profilePage);
      }
    },
    child: CustomAppBar(
      leading: CustomIconButton(
        svgPathUp: $AssetsIconsGen().iconApp.back,
        onPressed: () => Get.back(),
        elevation: 0,
      ),
      title: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(
            AppLocalizations.of(context)!.keyword_profile,
            style: Theme.of(context).textTheme.titleLarge,
          ),
        ],
      ),
      actions: [
        BlocBuilder<UserBloc, UserState>(
          builder: (context, state) {
            return CustomIconButton(
              onPressed: () {
                BlocProvider.of<UserBloc>(context).add(logoutEvent());
              },
              svgPathUp: $AssetsIconsGen().iconApp.logout,
              elevation: 0,
            );
          },
        ),
      ],
    ),
  );
}
