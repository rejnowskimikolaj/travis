./gradlew clean testDebugUnitTest
EXIT_CODE=$?
if [ ${EXIT_CODE} != 0 ]
then
  echo "Exit code of testDebugUnitTest is " ${EXIT_CODE}
  exit ${EXIT_CODE}
fi

./gradlew connectedAndroidTest --info
EXIT_CODE_ANDROID_TEST=$?
if [ ${EXIT_CODE_ANDROID_TEST} != 0 ]
then
  echo "Exit code of connectedAndroidTest is " ${EXIT_CODE_ANDROID_TEST}
  exit ${EXIT_CODE_ANDROID_TEST}
fi

echo "Build started"
./gradlew clean assembleDebug