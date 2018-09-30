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

@ManagedBean(name = "data")
@RequestScoped
public class ManagedBeanData {

    private Integer idgaseosa;
    private Integer[] idsgaseosa;
    private List<Gaseosas> list;

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

    public List<Gaseosas> getList() {
        list = new ArrayList<>();
        list.add(new Gaseosas(1, "Inca Kola"));
        list.add(new Gaseosas(2, "Pepsi Cola"));
        list.add(new Gaseosas(3, "Coca Cola"));
        list.add(new Gaseosas(4, "Fanta"));
        list.add(new Gaseosas(5, "Concordia"));
        return list;
    }

    public void setList(List<Gaseosas> list) {
        this.list = list;
    }

}
