/* To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor. */
package net.vproject.imageflow;

import java.util.List;

import net.vproject.imageio.ImgPlus;

/** @author Volker */
public class AntImageCopy extends AbstractImageList {
	private String category;

	@Override
	protected boolean filterImage(ImgPlus img) {
		if (category == null) {
			if (img.getRating() >= rating) {
				return true;
			}
		} else {
			final List<String> allTags = img.getInfo().getTags();
			if (allTags.contains(category) && img.getRating() >= rating) {
				return true;
			}
		}
		return false;
	}

	public void setcategory(String cat) {
		category = cat;
	}
}
