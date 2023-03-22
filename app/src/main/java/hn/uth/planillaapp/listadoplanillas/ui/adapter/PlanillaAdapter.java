package hn.uth.planillaapp.listadoplanillas.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import hn.uth.planillaapp.OnItemClickListener;

import hn.uth.planillaapp.databinding.PlanillaItemBinding;
import hn.uth.planillaapp.listadoplanillas.entity.Planilla;

public class PlanillaAdapter extends RecyclerView.Adapter<PlanillaAdapter.ViewHolder>{
    private List<Planilla> dataset;
    private OnItemClickListener<Planilla> manejadorEventoClick;

    public PlanillaAdapter(List<Planilla> dataset, OnItemClickListener<Planilla> manejadorEventoClick) {
        copiarDataset(dataset);
        this.manejadorEventoClick = manejadorEventoClick;
    }

    @NonNull
    @Override
    public PlanillaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlanillaItemBinding binding = PlanillaItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull PlanillaAdapter.ViewHolder holder, int position) {
        Planilla vehiculoMostrar = dataset.get(position);
        holder.binding.tvConcepto.setText(vehiculoMostrar.getConcepto());
        holder.binding.tvFecha.setText(vehiculoMostrar.getFecha());
        holder.binding.tvTotals.setText(String.valueOf(vehiculoMostrar.getTotals()));
        holder.setOnClickListener(vehiculoMostrar, manejadorEventoClick);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setItems(List<Planilla> datasetNuevo){
        copiarDataset(datasetNuevo);
        //this.dataset.addAll(datasetNuevo);
        notifyDataSetChanged();
    }

    public void copiarDataset(List<Planilla> datasetNuevo){
        if(this.dataset == null){
            this.dataset = new ArrayList<>();
        }
        this.dataset.clear();
        datasetNuevo.forEach( d -> {
            this.dataset.add(d);
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        PlanillaItemBinding binding;

        public ViewHolder(PlanillaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setOnClickListener(final Planilla planillaSeleccionado, final OnItemClickListener<Planilla> listner){
            this.binding.cardPlanilla.setOnClickListener(v-> listner.onItemClick(planillaSeleccionado, 1));
        }
    }
}
