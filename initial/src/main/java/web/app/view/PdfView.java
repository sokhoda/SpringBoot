package web.app.view;

import businessdomain.DomainHelper;
import businessdomain.Pizza;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        Pizza pizza = (Pizza) model.get(DomainHelper.PIZZA);
        PdfPTable pizzaTable = new PdfPTable(4);

        pizzaTable.addCell(DomainHelper.PIZZA_ID);
        pizzaTable.addCell(DomainHelper.PIZZA_NAME);
        pizzaTable.addCell(DomainHelper.PIZZA_TYPE);
        pizzaTable.addCell(DomainHelper.PIZZA_PRICE);

        pizzaTable.addCell(String.valueOf(pizza.getPizzaId()));
        pizzaTable.addCell(pizza.getName());
        pizzaTable.addCell(pizza.getType().name());
        pizzaTable.addCell(String.valueOf(pizza.getPrice()));

        document.add(pizzaTable);
    }
}
