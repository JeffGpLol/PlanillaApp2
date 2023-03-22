package hn.uth.planillaapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import hn.uth.planillaapp.listadoplanillas.entity.Planilla;
import hn.uth.planillaapp.listadoplanillas.entity.PlanillaDao;

public class PlanillaReposity {

    private PlanillaDao planillaDao;
    private LiveData<List<Planilla>> planillasDataset;

    public PlanillaReposity(Application app){
        PlanillaDatabase db = PlanillaDatabase.getDatabase(app);
        planillaDao = db.planillaDao();
        planillasDataset = planillaDao.getPlanillas();
    }

    public LiveData<List<Planilla>> getPlanillaDataset() {
        return planillasDataset;
    }

    public void insert(Planilla nuevo){
        PlanillaDatabase.databaseWriteExecutor.execute(() ->{
            planillaDao.insert(nuevo);
        });
    }

    public void update(Planilla actualizar){
        PlanillaDatabase.databaseWriteExecutor.execute(() ->{
            planillaDao.update(actualizar);
        });
    }

    public void delete(Planilla eliminar){
        PlanillaDatabase.databaseWriteExecutor.execute(() ->{
            planillaDao.delete(eliminar);
        });
    }

    public void deleteAll(){
        PlanillaDatabase.databaseWriteExecutor.execute(() ->{
            planillaDao.deleteAll();
        });
    }
}

