import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        /**
         * Создаем объект класса EmployeeBook
         */
        EmployeeBook eo = new EmployeeBook();

        /**
         * Создаю сотрудников и заполняю конструткор при помощи методов случайной генерации имен и чисел.
         * В задании этого не требовалось, но такую идею озвучил Алексей на разборе ДЗ,
         * а мне просто было интересно это сделать.
         */
        for (int i = 0; i < eo.getEmployees().length; i++) {
            eo.addEmployee(EmployeeBook.randomLastNameGeneration(eo.getManOrWomen()),
                    EmployeeBook.randomFirstNameGeneration(eo.getManOrWomen()),
                    EmployeeBook.randomMiddleNameGeneration(eo.getManOrWomen()),
                    EmployeeBook.randomNumGenerator(50_000) + 70_000,
                    EmployeeBook.randomNumGenerator(6));
        }


        /**
         * а) Получить список всех сотрудников со всеми имеющимися по ним данными
         * (вывести в консоль значения всех полей (toString)).*/
        System.out.println(eo.toString().toString());

        /**
         *Посчитать сумму затрат на зарплаты в месяц.
         * */
        System.out.println("Месячная сумма затрат на зарплаты");
        System.out.println(new DecimalFormat("###,###").format(eo.monthSumSalary()) + " pублей");
        System.out.println();

        /**
         * Найти сотрудника с минимальной зарплатой.
         */
        System.out.println("Сотрудник с минимальной зарплатой");
        eo.findAndPrintEmployeeById(eo.findEmployeesIdMinimalSalary());
        System.out.println();

        /**
         * Найти сотрудника с максимальной зарплатой.
         */
        System.out.println("Сотрудник с максимальной зарплатой");
        eo.findAndPrintEmployeeById(eo.findEmployeesIdMaximalSalary());
        System.out.println();

        /**
         * Подсчитать среднее значение зарплат (можно использовать для этого метод из пункта b).
         */
        System.out.println("Среднее значение зарплат");
        System.out.println(new DecimalFormat("###,###.##").
                format(eo.monthMiddleSalary(eo.monthSumSalary())) + " pублей");
        System.out.println();

        /**
         * Получить Ф. И. О. всех сотрудников (вывести в консоль)
         */
        System.out.println("ФИО всех сотрудников");
        eo.printAllEmployees();
        System.out.println();

        /**
         * Проиндексировать зарплату (вызвать изменение зарплат у всех сотрудников на величину аргумента в %).
         */
        System.out.println("Индексация зарплаты на стандартную величину 3,5%");
        eo.salaryIndexing();
        eo.printAllEmployees();
        System.out.println();

        /**
         * Проиндексировать зарплату всех сотрудников отдела на процент, который приходит в качестве параметра.
         */
        System.out.println("Индексация зарплаты на заданную величину");
        eo.salaryIndexingWithParam(12.6);
        eo.printAllEmployees();
        System.out.println();

        /**
         * Среднюю зарплату по отделу (учесть, что количество людей в отделе отличается от employees.length).
         */
        System.out.println("Средняя зарплата по отделу");
        if (eo.middleSalaryById(1) != -1) {
            System.out.println(new DecimalFormat("###,###.##").
                    format(eo.middleSalaryById(1)) + " рублей");
        }else {
            System.out.println("Отдела с таким номером не существует. Введите другой номер.");
        }
        System.out.println();

        /**
         * Напечатать всех сотрудников отдела (все данные, кроме отдела).
         */
        System.out.println("Все сотрудники без отделов");
        eo.printAllEmployeesWithoutDept();
        System.out.println();

        /**
         * Получить в качестве параметра число и найти:
         *     Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
         */
        System.out.println("Все сотрудники с зарплатой меньше определенного числа");
        eo.salaryLessThan(110_000);
        System.out.println();

        /**
         * Получить в качестве параметра число и найти:
         *      Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. и зарплатой в консоль).
         */
        System.out.println("Все сотрудники с зарплатой выше определенного числа");
        eo.salaryMoreThan(110_000);
        System.out.println();

        /**
         * 5. Изменить сотрудника (получить сотрудника по Ф. И. О., модернизировать его запись):
         *     1. Изменить зарплату.
         *     2. Изменить отдел.
         *     Придумать архитектуру. Сделать или два метода, или один, но продумать его.
         */

        System.out.println("Исходный список сотрудников");
        eo.printAllEmployees();
        eo.changeEmployee(1, 5, -1);
        System.out.println();
        System.out.println("Изменяем отдел");
        eo.printAllEmployees();
        System.out.println();
        System.out.println("Изменяем зарплату");
        eo.changeEmployee(1, -1, 60_000);
        eo.printAllEmployees();
        System.out.println();

        /**
         * Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).
         */
        System.out.println("Список отделов и сотрудников принадлежащих этим отделам");
        eo.printEmployeeAccordingToDept();

    }
}