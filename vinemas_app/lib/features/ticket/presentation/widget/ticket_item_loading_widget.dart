
import 'package:flutter/material.dart';
import 'package:packages/widget/Shimmer/custom_shimmer.dart';
import 'package:vinemas_app/core/config/app_color.dart';

class TicketItemLoadingWidget extends StatelessWidget {
  const TicketItemLoadingWidget({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12),
      ),
      color: AppColor.secondaryColor,
      child: Padding(
        padding: const EdgeInsets.all(12.0),
        child: Row(
          children: [
            CustomShimmer(
              width: 68,
              height: 108,
              borderRadius: 8,
              baseColor: AppColor.secondaryTextColor.withOpacity(0.3),
              highlightColor: AppColor.buttonLinerOneColor.withOpacity(
                0.6,
              ),
            ),
            const SizedBox(width: 10),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      CustomShimmer(
                        width: 100,
                        height: 20,
                        borderRadius: 8,
                        baseColor: AppColor.secondaryTextColor
                            .withOpacity(0.3),
                        highlightColor: AppColor.buttonLinerOneColor
                            .withOpacity(0.6),
                      ),
                      CustomShimmer(
                        width: 54,
                        height: 24,
                        borderRadius: 8,
                        baseColor: AppColor.secondaryTextColor
                            .withOpacity(0.3),
                        highlightColor: AppColor.buttonLinerOneColor
                            .withOpacity(0.6),
                      ),
                    ],
                  ),
                  const SizedBox(height: 10),
                  CustomShimmer(
                    width: 160,
                    height: 20,
                    borderRadius: 8,
                    baseColor: AppColor.secondaryTextColor.withOpacity(
                      0.3,
                    ),
                    highlightColor: AppColor.buttonLinerOneColor
                        .withOpacity(0.6),
                  ),
                  const SizedBox(height: 10),
                  CustomShimmer(
                    width: 200,
                    height: 20,
                    borderRadius: 8,
                    baseColor: AppColor.secondaryTextColor.withOpacity(
                      0.3,
                    ),
                    highlightColor: AppColor.buttonLinerOneColor
                        .withOpacity(0.6),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}