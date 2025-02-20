using E_mart.Dtos;

namespace E_mart.Services
{
    public interface IOrderService
    {
        IEnumerable<OrderDTO> GetAllOrders();
        OrderDTO GetOrderById(int id);
        void CreateOrder(OrderDTO orderDto);
        void UpdateOrder(int id, OrderDTO orderDto);
        void DeleteOrder(int id);
    }
}
