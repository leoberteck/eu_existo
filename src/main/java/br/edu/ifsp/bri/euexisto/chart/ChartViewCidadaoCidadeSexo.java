    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.bri.euexisto.chart;

import br.edu.ifsp.bri.euexisto.domain.CidadaoSexoQtde;
import br.edu.ifsp.bri.euexisto.domain.Cidade;
import br.edu.ifsp.bri.euexisto.domain.Estado;
import br.edu.ifsp.bri.euexisto.service.CidadaoService;
import br.edu.ifsp.bri.euexisto.service.CidadeService;
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
public class ChartViewCidadaoCidadeSexo implements Serializable {
    
    private Estado                estado;
    
    private List<Estado>          listaEstado;
    
    private BarChartModel         animatedModelCidade;
    
    private CidadaoService        cidadaoService             = new CidadaoService();
    private EstadoService         estadoService              = new EstadoService();
    
    private List<CidadaoSexoQtde> listaCidadaoCidadeSexoQtde;
 
    @PostConstruct
    public void init() {
        listaEstado = estadoService.list();
        createAnimatedModels();
    }
 
    public BarChartModel getAnimatedModelCidade() {
        return animatedModelCidade;
    }
 
    private void createAnimatedModels() {
        int qtdeMax = this.getCidadaoService().getCidadaoSexoQtdeMax();
       
        animatedModelCidade = initBarModelCidade();
        animatedModelCidade.setTitle("População por Cidade");
        animatedModelCidade.setAnimate(true);
        animatedModelCidade.setLegendPosition("ne");
        Axis yAxis = animatedModelCidade.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(qtdeMax + 1);
    }
    
    public void listCidade() {
        listaCidadaoCidadeSexoQtde = cidadaoService.listCidadaoCidadeSexoQtde(1);
    }
     
    private BarChartModel initBarModelCidade() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Homem");
        
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Mulher");
        
        for (int i=0; i<listaCidadaoCidadeSexoQtde.size(); i++) {
            boys.set(listaCidadaoCidadeSexoQtde.get(i).getValor(), listaCidadaoCidadeSexoQtde.get(i).getQtdeMasc());
            girls.set(listaCidadaoCidadeSexoQtde.get(i).getValor(), listaCidadaoCidadeSexoQtde.get(i).getQtdeFem());
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
        
    public List<CidadaoSexoQtde> getListaCidadaoCidadeSexoQtde() {
        return listaCidadaoCidadeSexoQtde;
    }

    public void setListaCidadaoCidadeSexoQtde(List<CidadaoSexoQtde> listaCidadaoCidadeSexoQtde) {
        this.listaCidadaoCidadeSexoQtde = listaCidadaoCidadeSexoQtde;
    }    
}// fim da classe ChartViewCidadaoEstadoSexo
