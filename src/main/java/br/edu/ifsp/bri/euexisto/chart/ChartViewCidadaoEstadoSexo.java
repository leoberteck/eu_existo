    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.chart;

import br.edu.ifsp.bri.euexisto.domain.CidadaoSexoQtde;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.service.CidadaoService;
import br.edu.ifsp.bri.euexisto.service.EstadoService;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
 
/**
 *
 * @author gahsabio
 */
@ManagedBean
public class ChartViewCidadaoEstadoSexo implements Serializable {
 
    private Estado                estado;
    private List<Estado>          listaEstado;
    
    private BarChartModel         animatedModelEstado;
    
    private CidadaoService        cidadaoService             = new CidadaoService();
    private EstadoService         estadoService              = new EstadoService();
    
    private List<CidadaoSexoQtde> listaCidadaoEstadoSexoQtde = this.getCidadaoService().listCidadaoEstadoSexoQtde();
 
    @PostConstruct
    public void init() {
        listaEstado                 = estadoService.list();
        createAnimatedModels();
    }
 
    public BarChartModel getAnimatedModelEstado() {
        return animatedModelEstado;
    }
 
    private void createAnimatedModels() {
        int qtdeMax = this.getCidadaoService().getCidadaoSexoQtdeMax();
       
        animatedModelEstado = initBarModelEstado();
        animatedModelEstado.setTitle("População por Estado");
        animatedModelEstado.setAnimate(true);
        animatedModelEstado.setLegendPosition("ne");
        Axis yAxis = animatedModelEstado.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(qtdeMax + 1);
    }
     
    private BarChartModel initBarModelEstado() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Homem");
        
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Mulher");
        
        for (int i=0; i<listaCidadaoEstadoSexoQtde.size(); i++) {
            boys.set(listaCidadaoEstadoSexoQtde.get(i).getValor(), listaCidadaoEstadoSexoQtde.get(i).getQtdeMasc());
            girls.set(listaCidadaoEstadoSexoQtde.get(i).getValor(), listaCidadaoEstadoSexoQtde.get(i).getQtdeFem());
        }
 
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
         
    public CidadaoService getCidadaoService() {
        return cidadaoService;
    }
    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public List<Estado> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<Estado> listaEstado) {
        this.listaEstado = listaEstado;
    }
        
    public List<CidadaoSexoQtde> getListaCidadaoEstadoSexoQtde() {
        return listaCidadaoEstadoSexoQtde;
    }

    public void setListaCidadaoEstadoSexoQtde(List<CidadaoSexoQtde> listaCidadaoEstadoSexoQtde) {
        this.listaCidadaoEstadoSexoQtde = listaCidadaoEstadoSexoQtde;
    }    
}// fim da classe ChartViewCidadaoEstadoSexo
