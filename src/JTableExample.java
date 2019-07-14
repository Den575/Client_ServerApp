import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class JTableExample {

    //Массив содержащий заголоки таблицы
    Object[] headers = { "Name", "Surname", "Telephone" };

    //Массив содержащий информацию для таблицы
    Object[][] data = {
            { "John", "Smith", "1112221" },
            { "Ivan", "Black", "2221111" },
            { "George", "White", "3334444" },
            { "Bolvan", "Black", "2235111" },
            { "Serg", "Black", "2221511" },
            { "Pussy", "Black", "2221111" },
            { "Tonya", "Red", "2121111" },
            { "Elise", "Green", "2321111" },
    };

    //Объект таблицы
    JTable jTabPeople;

    JTableExample() {
        //Создаем новый контейнер JFrame
        JFrame jfrm = new JFrame("JTableExample");
        //Устанавливаем диспетчер компоновки
        jfrm.getContentPane().setLayout(new FlowLayout());
        //Устанавливаем размер окна
        jfrm.setSize(300, 170);
        //Устанавливаем завершение программы при закрытии окна
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Создаем новую таблицу на основе двумерного массива данных и заголовков
        jTabPeople = new JTable(data, headers);
        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
        JScrollPane jscrlp = new JScrollPane(jTabPeople);
        //Устаналиваем размеры прокручиваемой области
        jTabPeople.setPreferredScrollableViewportSize(new Dimension(250, 100));
        //Добавляем в контейнер нашу панель прокрути и таблицу вместе с ней
        jfrm.getContentPane().add(jscrlp);
        //Отображаем контейнер
        jfrm.setVisible(true);
    }

    //Функция main, запускающаяся при старте приложения
    public static void main(String[] args) {
        //Создаем фрейм в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JTableExample();
            }
        });
    }
}