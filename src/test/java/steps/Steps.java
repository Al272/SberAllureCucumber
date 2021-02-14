package steps;

import io.cucumber.datatable.DataTable;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import ru.appline.framework.managers.ManagerPages;

import java.util.ArrayList;
import java.util.List;

public class Steps {
    /**
     * Менеджер страничек
     * @see ManagerPages#getManagerPages()
     */
    private ManagerPages app = ManagerPages.getManagerPages();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage(){
        app.getStartPage();
    }

    @Когда("^Переход в главное меню '(.*)'$")
    public void selectNameBaseMenu(String nameBaseMenu){
        app.getStartPage().selectBaseMenu(nameBaseMenu);
    }

    @Когда("^Выбираем подменю '(.*)'$")
    public void selectNameSubMenu(String nameSubMenu){
        app.getStartPage().selectSubMenu(nameSubMenu);
    }

    @Тогда("^Проверка открытия страницы '(.*)'$")
    public void checkPageTitle(String title) {
        app.getIpotekaPage().checkOpenPage(title);
    }


    @Когда("^Заполняем форму поле/значение$")
    public void fillFields(DataTable dataTable){
        final List<String> temp = new ArrayList<>();
        dataTable.cells().forEach(
                raw -> {
                    temp.add(raw.get(1));
                }
        );
        app.getIpotekaPage().insertParams(temp.get(0), temp.get(1), temp.get(2));
    }

    @Когда("^Нажимаем на 'Ролики'$")
    public void clickRolls(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getIpotekaPage().checkRollButton(raw.get(0), Boolean.parseBoolean(raw.get(1)));
                }
        );
    }

    @Тогда("^Проверяем корректность введенных данных$")
    public void checkErrorMessageAtField(DataTable dataTable){
        dataTable.cells().forEach(
                raw -> {
                    app.getIpotekaPage().checkPayData(raw.get(0), raw.get(1));
                }
        );
    }

}
