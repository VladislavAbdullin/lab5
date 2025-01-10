// Интерфейс для работы с дробью
interface DrobOperations {
    double getValue();
    void setNumerator(int numerator);  // Установить числитель
    void setDenominator(int denominator);  // Установить знаменатель
}

// Класс для представления дроби
class Drob implements DrobOperations {
    private int numerator;  // Числитель
    private int denominator;  // Знаменатель
    private Double cachedValue;  // Кэшированное вещественное значение

    // Конструктор: принимаем числитель и знаменатель
    public Drob(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть нулем");
        }

        // Если знаменатель отрицательный, меняем знак у числителя и знаменателя
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
        this.cachedValue = null;  // Сбросить кэшированное значение
    }

    // Получение вещественного значения дроби (с кэшированием)
    @Override
    public double getValue() {
        if (cachedValue == null) {  // Если значение не кэшировано
            cachedValue = (double) numerator / denominator;  // Считаем вещественное значение
        }
        return cachedValue;
    }

    // Установка числителя
    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
        this.cachedValue = null;  // Сбросить кэш, так как значение может измениться
    }

    // Установка знаменателя
    @Override
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть нулем");
        }

        // Если знаменатель отрицательный, меняем знак у числителя и знаменателя
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.denominator = denominator;
        this.cachedValue = null;  // Сбросить кэш
    }

    // Строковое представление дроби
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Метод сравнения дробей
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Проверяем на ссылочную равенство
        if (obj == null || getClass() != obj.getClass()) return false;  // Проверка типа объекта
        Drob drob = (Drob) obj;
        return numerator == drob.numerator && denominator == drob.denominator;  // Сравнение числителя и знаменателя
    }

    // Переопределение hashCode для корректного использования в коллекциях
    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }
}