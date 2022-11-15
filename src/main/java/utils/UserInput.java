package utils;

import camp.nextstep.edu.missionutils.Console;
import model.Lotto;
import model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInput {
    private static Game game;

    public UserInput(Game game) {
        this.game = game;
    }

    // 구입금액 입력
    public static void inputMoney() {
        String userInputMoney = Console.readLine();

        validateInputIsNum(userInputMoney);
        validateInputMoney(userInputMoney);

        int lottoCount = getLottoCount(userInputMoney);
        game.setPurchaseNumber(lottoCount);
    }

    // 로또 개수
    public static int getLottoCount(String userInput) {
        int userInputMoney = Integer.parseInt(userInput);

        return userInputMoney / 1000;
    }

    // 당첨번호 입력
    public static void inputLottoNumber() {
        String userInputLottoNumbers = Console.readLine();

        validateInputLottoNumbers(userInputLottoNumbers);
        List<Integer> lottoNumbers = inputLottoNumberToList(userInputLottoNumbers);
        Lotto lotto = new Lotto(lottoNumbers);

        game.setLotto(lotto);
    }

    // 보너스번호 입력
    public static void inputBonusNumber() {
        String userInputBonusNumber = Console.readLine();

        validateInputIsNum(userInputBonusNumber);
        validateNumberRange(userInputBonusNumber);
        validateDuplicateBonusNumber(userInputBonusNumber);

        game.setBonusNumber(Integer.parseInt(userInputBonusNumber));
    }

    // 사용자 입력이 숫자인지 유효성 검사
    private static void validateInputIsNum(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 숫자 혹은 형식에 맞게 입력해주세요.");
        }
    }

    // 사용자 입력금액이 1000으로 나눠 떨어지는지 유효성 검사
    private static void validateInputMoney(String userInput) {
        int userInputMoney = Integer.parseInt(userInput);

        if (userInputMoney % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000원 단위로만 입력해주세요.");
        }
    }

    // 입력되는 당첨번호가 형식에 맞는지 유효성 검사
    private static void validateInputLottoNumbers(String userInput) {
        StringTokenizer st = new StringTokenizer(userInput, ",");
        int tokenCount = st.countTokens();

        while (st.hasMoreTokens()) {
            validateInputIsNum(st.nextToken());
        }

        if (tokenCount != 6) {
            throw new IllegalArgumentException("[ERROR] 형식에 맞게 입력해주세요. 번호는 쉼표(,)를 기준으로 구분합니다.");
        }
    }

    // 입력되는 당첨번호를 리스트로 변환
    private static List<Integer> inputLottoNumberToList(String userInput) {
        StringTokenizer st = new StringTokenizer(userInput, ",");
        List<Integer> lottoNumbers = new ArrayList<>();

        while (st.hasMoreTokens()) {
            lottoNumbers.add(Integer.parseInt(st.nextToken()));
        }

        return lottoNumbers;
    }

    // 보너스번호가 당첨번호와 중복되는지 유효성 검사
    private static void validateDuplicateBonusNumber(String userInput) {
        int userInputBonusNumber = Integer.parseInt(userInput);
        Lotto lotto = game.getLotto();
        List<Integer> lottoNumbers = lotto.getNumbers();

        for (Integer lottoNumber : lottoNumbers) {
            if (lottoNumber == userInputBonusNumber) {
                throw new IllegalArgumentException("[ERROR] 당첨번호와 중복되지 않는 보너스번호를 입력해주세요.");
            }
        }
    }

    // 번호가 1부터 45까지의 범위인지 확인
    private static void validateNumberRange(String userInput) {
        int userInputNumber = Integer.parseInt(userInput);

        if (userInputNumber < 1 || userInputNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 1부터 45까지의 숫자만 입력해주세요.");
        }
    }
}
