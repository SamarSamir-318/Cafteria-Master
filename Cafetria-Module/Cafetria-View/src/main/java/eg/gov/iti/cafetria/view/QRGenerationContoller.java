/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.gov.iti.cafetria.view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import eg.gov.iti.cafetria.model.dal.domain.Order;
import eg.gov.iti.cafetria.service.OrderService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author atom
 */
@Controller
public class QRGenerationContoller {

	@Autowired
	OrderService orderService;

	@ResponseBody
	@RequestMapping(value = "qrCode/{itemId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] toQRCode(@PathVariable String itemId) throws WriterException, IOException {
		//String qrCodeData = "Hello World!";
		//String filePath = "QRCode.png";

		byte[] resultQr = {};
		int id;
		
		try {
			id = Integer.parseInt(itemId);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return resultQr;
		}
		
		Order order = orderService.findOne(id);
		
		if(order == null) {
			System.out.println("ERROR: Order doesn't exist!");
			return resultQr;
		}
		
		String charset = "UTF-8"; // or "ISO-8859-1"
		int qrCodewidth = 500;
		int qrCodeheight = 500;

		Map hintMap = new HashMap();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(itemId.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		BufferedImage img = MatrixToImageWriter.toBufferedImage(matrix);

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIO.write(img, "png", baos);
			baos.flush();
			resultQr = baos.toByteArray();
		}

		return resultQr;
	}
}
