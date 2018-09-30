/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.bean;

import dto.Gaseosas;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "data")
@RequestScoped
public class ManagedBeanData {

    private Integer idgaseosa;
    private Integer[] idsgaseosa;
    private List<SelectItem> list;

    public Integer getIdgaseosa() {
        return idgaseosa;
    }

    public void setIdgaseosa(Integer idgaseosa) {
        this.idgaseosa = idgaseosa;
    }

    public Integer[] getIdsgaseosa() {
        return idsgaseosa;
    }

    public void setIdsgaseosa(Integer[] idsgaseosa) {
        this.idsgaseosa = idsgaseosa;
    }

    public List<SelectItem> getList() {
        list = new ArrayList<>();
        list.add(new SelectItem(1, "Inca Kola"));
        list.add(new SelectItem(2, "Pepsi Cola"));
        list.add(new SelectItem(3, "Coca Cola"));
        list.add(new SelectItem(4, "Fanta"));
        list.add(new SelectItem(5, "Concordia"));
        return list;
    }

    public void setList(List<SelectItem> list) {
        this.list = list;
    }

}
