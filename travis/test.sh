./gradlew clean testDebugUnitTest
EXIT_CODE=$?
if [ ${EXIT_CODE} != 0 ]
then
  echo "Exit code of testDebugUnitTest is " ${EXIT_CODE}
  exit ${EXIT_CODE}
fi

echo "Building started"
./gradlew clean assembleDebug