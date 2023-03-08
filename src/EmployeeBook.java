import java.text.DecimalFormat;
import java.util.Arrays;

public class EmployeeBook {
    private final Employee[] employees;
    private static int idCounter;
    private int manOrWomen = randomNumGenerator(2);

    public EmployeeBook() {
        this.employees = new Employee[10];
    }

    @Override
    public String toString() {
        return "EmployeeOperaions{" +
                "employees=" + Arrays.toString(employees) + "\n" +
                ", idCounter=" + idCounter +
                '}';
    }

    public void addEmployee(String lastName, String middleName, String firstName, int salary, int dept) {
        if (idCounter >= employees.length) {
            System.out.println("Нельзя добавить работника, закончилось место");
        }
        Employee newEmployee = new Employee(lastName, middleName, firstName, salary, dept);
        employees[idCounter++] = newEmployee;
    }

    public void deleteEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                System.out.println(employees[i].getFirstName() + " " + employees[i].getMiddleName() + " " +
                        employees[i].getLastName() + " удален");
                System.arraycopy(employees, i + 1, employees, i, idCounter - i - 1);
                employees[idCounter - 1] = null;
                idCounter--;
                return;
            }
        }
    }

    public void changeEmployee(int idEmployee, int deptChanging, int salary) throws Exception {
        if (deptChanging > 5) {
            throw new Exception("Номер департамента не может быть больше 5");
        }
        for (int i = 0; i < idCounter; i++) {
            if (employees[i].getId() == idEmployee && deptChanging != -1 && salary != -1) {
                employees[i].setDept(deptChanging);
                employees[i].setSalary(salary);
            } else if (employees[i].getId() == idEmployee && deptChanging != -1) {
                employees[i].setDept(deptChanging);
            } else if (employees[i].getId() == idEmployee && salary != -1) {
                employees[i].setSalary(salary);
            }
        }
    }

    public void printAllEmployees() {
        for (int i = 0; i < idCounter; i++) {
            Employee employee = employees[i];
            System.out.println(employee.getLastName() + " " + employee.getMiddleName() + " " +
                    employee.getFirstName() + ": " + employee.getSalary() + ", отдел: " +
                    employee.getDept() + ", id: " + employee.getId());
        }
    }

    public void printAllEmployeesWithoutDept() {
        for (int i = 0; i < idCounter; i++) {
            Employee employee = employees[i];
            System.out.println(employee.getLastName() + " " + employee.getMiddleName() + " " +
                    employee.getFirstName() + ": " + employee.getSalary() + ", id: " + employee.getId());
        }
    }

    public void printEmployeeAccordingToDept() {
        int[] arrDept = new int[idCounter];
        /**
         * Пытаюсь получить упорядоченный список существующих отделов, где отделы не повторяются.
         * Для этого:
         * Сохдаю массив arrDept и записываю в него номера существующих отделов в произвольном порядке.
         */
        for (int i = 0; i < idCounter; i++) {
            arrDept[i] = employees[i].getDept();
        }
        System.out.println("Несортированный массив arrDept: " + Arrays.toString(arrDept));
        Arrays.sort(arrDept);
        System.out.println("Сортированный массив arrDept: " + Arrays.toString(arrDept));
        int arrElCounter = 1;
        /**
         * После сортировки массива в нем могут быть одинаковые отделы
         * Создаю переменную arrElCounter, которая просуммирует уникальные отделы, и станет количеством элементов
         * в массиве с сортированными неповторяющимися отделами.
         */
        for (int i = 0; i < idCounter - 1; i++) {
            if (arrDept[i] != arrDept[i + 1]) {
                arrElCounter++;
            }
        }
        /**
         * Переменная заполнилась и теперь можно создать итоговый массив с отделами arrDeptUnic.
         */
        int[] arrDeptUnic = new int[arrElCounter];
        /**
         * Теперь нужно пройтись циклом по сортированному массиву arrDept,
         * выбрать из него неповторяющиеся номера отделов, и записать их в массив arrDeptUnic
         * который имеет необходимое количество элементов.
         */
        arrElCounter = 0;
        System.out.println("Пустой массив arrDeptUnic: " + Arrays.toString(arrDeptUnic));
        for (int i = 0; i < idCounter - 1; i++) {
            if (arrDept[i] != arrDept[i + 1]) {
//                arrDeptUnic[arrElCounter]=arrDept[i];
                System.arraycopy(arrDept, i, arrDeptUnic, arrElCounter, 1);
                arrElCounter++;
            }
        }
        arrDeptUnic[arrElCounter] = arrDept[arrDept.length - 1];
        System.out.println("Заполненный массив arrDeptUnic: " + Arrays.toString(arrDeptUnic));
        /**
         * Массив с существующими отделами получен, и теперь можно найти
         * каждого сотрудника принадлежащего своему отделу и вывести его в консоль.
         * Для этого создаю цикл в цикле. Внешний цикл - это перебор отделов. Внутренний
         * цикл это перебор сотрудников и отбор тех чей номер отдела совпадает с
         * номерм отдела во внешнем цикле.
         */
        for (int i = 0; i < arrDeptUnic.length; i++) {
            System.out.println();
            System.out.println("Отдел № "+ arrDeptUnic[i] + ":");
            for (int j = 0; j < arrDept.length; j++) {
                if (employees[j].getDept() == arrDeptUnic[i]) {
                    System.out.println(" " + employees[j].getLastName() + " " +
                            employees[j].getMiddleName() + " " +
                            employees[j].getFirstName());
                }
            }
        }
    }

    public void salaryIndexing() {
        double percentOfIndexing = 3.5;
        for (int i = 0; i < idCounter; i++) {
            double increasedSalary = employees[i].getSalary() * (1 + percentOfIndexing / 100);
            employees[i].setSalary((int) increasedSalary);
        }
    }

    public void salaryLessThan(int lessThanThisNum) {
        int lessSalariesCounter = 0;
        for (int i = 0; i < idCounter; i++) {
            if (employees[i].getSalary() < lessThanThisNum) {
                System.out.println("id: " + employees[i].getId() + ", полное имя: " +
                        employees[i].getLastName() + " " + employees[i].getFirstName() + " " +
                        employees[i].getMiddleName() + ", зарплата: " + employees[i].getSalary() + " рублей");
                lessSalariesCounter++;
            }
        }
        if (lessSalariesCounter == 0) {
            System.out.println("Сотрудников с зарплатой ниже " + lessThanThisNum + " рублей " + " - нет");
        }
    }

    public void salaryMoreThan(int moreThanThisNum) {
        int moreSalariesCounter = 0;
        for (int i = 0; i < idCounter; i++) {
            if (employees[i].getSalary() >= moreThanThisNum) {
                System.out.println("id: " + employees[i].getId() + ", полное имя: " +
                        employees[i].getLastName() + " " + employees[i].getFirstName() + " " +
                        employees[i].getMiddleName() + ", зарплата: " + employees[i].getSalary() + " рублей");
                moreSalariesCounter++;
            }
        }
        if (moreSalariesCounter == 0) {
            System.out.println("Сотрудников с зарплатой выше " + moreThanThisNum + " рублей " + " - нет");
        }
    }

    public void salaryIndexingWithParam(double percentOfIndexing) {
        for (int i = 0; i < idCounter; i++) {
            double increasedSalary = employees[i].getSalary() * (1 + percentOfIndexing / 100);
            employees[i].setSalary((int) increasedSalary);
        }
    }

    public static int randomNumGenerator(int limitGeneration) {
        java.util.Random random = new java.util.Random();
        int randomNum = random.nextInt(limitGeneration);
        return randomNum;
    }

    public static String randomFirstNameGeneration(int manOrWomen) {
        String firstName = "";
        //Итоговая строка, куда будут записываться сгенерированные имена

        String[] firstMaleName = {"Александр", "Вадим", "Геннадий", "Анаколий", "Дмитрий", "Егор", "Иван"};
        String[] firstFemaleName = {"Анна", "Варвара", "Елена", "Елизавета", "Аэлита", "Элеонора", "Анжелика"};

        //массивы с вариантами женских м мужских имен, фамилий и отчеств

        if (manOrWomen == 0) {                   //если 0 то имя мужское, если 1 то - женское
            firstName = firstMaleName[randomNumGenerator(firstMaleName.length)];
            //генерируем рандомные числа и подставляем их в элементы массивов, а затем "собираем" строку
        } else {
            firstName = firstFemaleName[randomNumGenerator(firstFemaleName.length)];
        }
        return firstName;
    }

    public static String randomMiddleNameGeneration(int manOrWomen) {
        String middleName = "";
        //Итоговая строка, куда будут записываться сгенерированные имена

        String[] middleMaleName = {"Матвеевич", "Иванович", "Павлович", "Романович", "Семенович", "Тимофеевич",
                "Богданович"};
        String[] middleFemaleName = {"Матвеевна", "Ивановна", "Павловна", "Романовна", "Семеновна", "Тимофеевна",
                "Богдановна"};

        //массивы с вариантами женских м мужских имен, фамилий и отчеств

        if (manOrWomen == 0) {                   //если 0 то имя мужское, если 1 то - женское
            middleName = middleMaleName[randomNumGenerator(middleMaleName.length)];
            //генерируем рандомные числа и подставляем их в элементы массивов, а затем "собираем" строку
        } else {
            middleName = middleFemaleName[randomNumGenerator(middleFemaleName.length)];
        }
        return middleName;
    }

    public static String randomLastNameGeneration(int manOrWomen) {
        String LastName = "";
        //Итоговая строка, куда будут записываться сгенерированные имена

        String[] lastMaleName = {"Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев", "Петров", "Соколов"};
        String[] lastFemaleName = {"Иванова", "Смирнова", "Кузнецова", "Попова", "Васильева", "Петрова", "Соколова"};
        //массивы с вариантами женских м мужских имен, фамилий и отчеств

        if (manOrWomen == 0) {                   //если 0 то имя мужское, если 1 то - женское
            LastName = lastMaleName[randomNumGenerator(lastMaleName.length)];
            //генерируем рандомные числа и подставляем их в элементы массивов, а затем "собираем" строку
        } else {
            LastName = lastFemaleName[randomNumGenerator(lastFemaleName.length)];
        }
        return LastName;
    }

    public int getManOrWomen() {
        return manOrWomen;
    }

    public int findEmployeesIdMinimalSalary() {
        int min = employees[0].getSalary();
        int idEmployee = 0;
        for (int i = 0; i < idCounter; i++) {
            if (employees[i].getSalary() <= min) {
                min = employees[i].getSalary();
                idEmployee = employees[i].getId();
            }
        }
        return idEmployee;
    }

    public int findEmployeesIdMaximalSalary() {
        int max = employees[0].getSalary();
        int idEmployee = 0;
        for (int i = 0; i < idCounter; i++) {
            if (employees[i].getSalary() > max) {
                max = employees[i].getSalary();
                idEmployee = employees[i].getId();
            }
        }
        return idEmployee;
    }

    public void findAndPrintEmployeeById(int id) {
        for (int i = 0; i < idCounter; i++) {
            if (id == employees[i].getId()) {
                System.out.println(employees[i].getLastName() + " " + employees[i].getMiddleName() + " " +
                        employees[i].getFirstName() + ": " +
                        new DecimalFormat("###,###").format(employees[i].getSalary()) + ", отдел: " +
                        employees[i].getDept() + ", id: " + employees[i].getId());
            }
        }
    }

    public int monthSumSalary() {
        int sum = 0;
        for (int i = 0; i < idCounter; i++) {
            sum = employees[i].getSalary() + sum;
        }
        return sum;
    }

    public double monthMiddleSalary(int sum) {
        return (double) sum / idCounter;
    }

    public double middleSalaryById(int idOfEmployee) {
        int sumSalaries = 0;
        int deptsCounter = 0;
        for (int i = 0; i < idCounter; i++) {
            if (employees[i].getDept() == idOfEmployee) {
                sumSalaries = employees[i].getSalary() + sumSalaries;
                deptsCounter++;
            }
            if (sumSalaries == 0) {
                return -1;
            }
        }
        return (double) sumSalaries / deptsCounter;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        EmployeeBook.idCounter = idCounter;
    }

    public Employee[] getEmployees() {
        return employees;

    }
}
